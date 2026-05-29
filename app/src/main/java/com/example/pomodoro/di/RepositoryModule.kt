package com.example.pomodoro.di

import com.example.pomodoro.domain.repository.DonationRepository
import com.example.pomodoro.domain.repository.ProjectRepository
import com.example.pomodoro.domain.repository.SessionRepository
import com.example.pomodoro.domain.repository.UserRepository
import com.example.pomodoro.data.repository.DonationRepositoryImpl
import com.example.pomodoro.data.repository.ProjectRepositoryImpl
import com.example.pomodoro.data.repository.SessionRepositoryImpl
import com.example.pomodoro.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
    @Binds
    @Singleton
    abstract fun bindSessionRepository(impl: SessionRepositoryImpl): SessionRepository
    @Binds
    @Singleton
    abstract fun bindProjectRepository(impl: ProjectRepositoryImpl): ProjectRepository
    @Binds
    @Singleton
    abstract fun bindDonationRepository(impl: DonationRepositoryImpl): DonationRepository
}