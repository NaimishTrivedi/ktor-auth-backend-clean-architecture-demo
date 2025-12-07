package com.example.di

import com.example.data.repository.AuthRepository
import com.example.data.repository.UserRepository
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignupUseCase
import org.koin.dsl.module

val appModule = module {

    // Repository
    single { AuthRepository() }

    // UseCase
    single { LoginUseCase(get()) }  // get() injects AuthRepository

    single { UserRepository() }

    single { SignupUseCase(get()) }
}