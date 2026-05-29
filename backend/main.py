from fastapi import FastAPI
from pydantic import BaseModel
from datetime import datetime
import sqlite3

app = FastAPI()
DB_PATH = "pomodoro.db"


def get_db():
    conn = sqlite3.connect(DB_PATH)
    conn.row_factory = sqlite3.Row
    conn.execute(
        "CREATE TABLE IF NOT EXISTS sessions "
        "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
        "user_id INTEGER, focus_minutes INTEGER, completed_at TEXT)"
    )
    return conn


class SessionIn(BaseModel):
    user_id: int
    focus_minutes: int
    completed_at: str | None = None


@app.get("/sessions")
def list_sessions(user_id: int):
    db = get_db()
    rows = db.execute(
        "SELECT * FROM sessions WHERE user_id = ? ORDER BY completed_at DESC",
        (user_id,),
    ).fetchall()
    return [dict(r) for r in rows]


@app.post("/sessions")
def create_session(session: SessionIn):
    db = get_db()
    ts = session.completed_at or datetime.utcnow().isoformat()
    cur = db.execute(
        "INSERT INTO sessions (user_id, focus_minutes, completed_at) VALUES (?, ?, ?)",
        (session.user_id, session.focus_minutes, ts),
    )
    db.commit()
    return {
        "id": cur.lastrowid,
        "user_id": session.user_id,
        "focus_minutes": session.focus_minutes,
        "completed_at": ts,
    }


@app.put("/sessions/{id}")
def update_session(id: int, session: SessionIn):
    db = get_db()
    db.execute(
        "UPDATE sessions SET focus_minutes = ? WHERE id = ?",
        (session.focus_minutes, id),
    )
    db.commit()
    row = db.execute("SELECT * FROM sessions WHERE id = ?", (id,)).fetchone()
    return dict(row) if row else {"error": "not found"}


@app.delete("/sessions/{id}")
def delete_session(id: int):
    db = get_db()
    db.execute("DELETE FROM sessions WHERE id = ?", (id,))
    db.commit()
    return {"ok": True}


@app.get("/sessions/stats")
def get_stats(user_id: int):
    db = get_db()
    row = db.execute(
        "SELECT COUNT(*) as count, COALESCE(SUM(focus_minutes), 0) as total_minutes "
        "FROM sessions WHERE user_id = ?",
        (user_id,),
    ).fetchone()
    return {"total_sessions": row["count"], "total_focus_minutes": row["total_minutes"]}
