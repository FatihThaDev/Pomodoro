package com.example.pomodoro.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pomodoro.data.local.dao.DonationDao
import com.example.pomodoro.data.local.dao.ProjectDao
import com.example.pomodoro.data.local.dao.SessionDao
import com.example.pomodoro.data.local.dao.SessionProjectDao
import com.example.pomodoro.data.local.dao.UserDao
import com.example.pomodoro.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "pomodoro_db")
            .fallbackToDestructiveMigration(true)
            .addCallback(SEED_CALLBACK)
            .build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
    @Provides
    fun provideSessionDao(db: AppDatabase): SessionDao = db.sessionDao()
    @Provides
    fun provideProjectDao(db: AppDatabase): ProjectDao = db.projectDao()
    @Provides
    fun provideDonationDao(db: AppDatabase): DonationDao = db.donationDao()
    @Provides
    fun provideSessionProjectDao(db: AppDatabase): SessionProjectDao = db.sessionProjectDao()

    private val SEED_CALLBACK = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL("INSERT INTO users (username, email, password) VALUES ('Guest', 'guest@example.com', '')")
            db.execSQL("INSERT INTO projects (name, description) VALUES ('FatihNvim', 'Neovim config for power users')")
            db.execSQL("INSERT INTO projects (name, description) VALUES ('archlinux-install', 'Arch Linux install script')")
            db.execSQL("INSERT INTO projects (name, description) VALUES ('foodify-delivery-app', 'Next.js + Tailwind food delivery app')")
            db.execSQL("INSERT INTO projects (name, description) VALUES ('Cave-Slayers', '2D action game in Python')")
        }
    }
}
