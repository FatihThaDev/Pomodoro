package com.example.pomodoro.data.remote.api

import com.example.pomodoro.data.remote.dto.SessionDto
import com.example.pomodoro.data.remote.dto.StatsDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class CreateSessionRequest(
    val user_id: Long,
    val focus_minutes: Int
)

data class UpdateSessionRequest(
    val focus_minutes: Int
)

interface PomodoroApi {
    @GET("sessions")
    suspend fun getSessions(@Query("user_id") userId: Long): List<SessionDto>

    @POST("sessions")
    suspend fun createSession(@Body request: CreateSessionRequest): SessionDto

    @PUT("sessions/{id}")
    suspend fun updateSession(@Path("id") id: Long, @Body request: UpdateSessionRequest): SessionDto

    @DELETE("sessions/{id}")
    suspend fun deleteSession(@Path("id") id: Long)

    @GET("sessions/stats")
    suspend fun getStats(@Query("user_id") userId: Long): StatsDto
}
