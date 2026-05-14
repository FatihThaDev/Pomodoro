package com.example.pomodoro.model.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pomodoro.model.data.local.dao.DonationDao
import com.example.pomodoro.model.data.local.dao.ProjectDao
import com.example.pomodoro.model.data.local.dao.SessionDao
import com.example.pomodoro.model.data.local.dao.SessionProjectDao
import com.example.pomodoro.model.data.local.dao.UserDao
import com.example.pomodoro.model.data.local.entity.DonationEntity
import com.example.pomodoro.model.data.local.entity.ProjectEntity
import com.example.pomodoro.model.data.local.entity.SessionEntity
import com.example.pomodoro.model.data.local.entity.SessionProjectCrossRef
import com.example.pomodoro.model.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        SessionEntity::class,
        ProjectEntity::class,
        DonationEntity::class,
        SessionProjectCrossRef::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sessionDao(): SessionDao
    abstract fun projectDao(): ProjectDao
    abstract fun donationDao(): DonationDao
    abstract fun sessionProjectDao(): SessionProjectDao
}
