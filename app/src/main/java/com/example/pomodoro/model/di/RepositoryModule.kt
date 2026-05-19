package com.example.pomodoro.model.di

import com.example.pomodoro.model.repository.DonationRepository
import com.example.pomodoro.model.repository.ProjectRepository
import com.example.pomodoro.model.repository.SessionRepository
import com.example.pomodoro.model.repository.UserRepository
import com.example.pomodoro.model.repository.impl.DonationRepositoryImpl
import com.example.pomodoro.model.repository.impl.ProjectRepositoryImpl
import com.example.pomodoro.model.repository.impl.SessionRepositoryImpl
import com.example.pomodoro.model.repository.impl.UserRepositoryImpl
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