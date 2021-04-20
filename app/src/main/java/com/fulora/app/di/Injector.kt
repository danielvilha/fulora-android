package com.fulora.app.di

import android.content.Context
import com.fulora.app.ui.home.HomeViewModel
import com.fulora.app.ui.home.HomeViewModelFactory
import com.fulora.app.usecase.PlantingAreaUseCase
import com.fulora.app.repositories.PlantRepository
import com.fulora.app.repositories.PlantingAreaRepository
import com.fulora.app.repositories.UserRepository
import com.fulora.app.source.local.FuloraDatabase
import com.fulora.app.ui.offline.sign_in.SignInFragment
import com.fulora.app.ui.offline.sign_in.SignInViewModel
import com.fulora.app.ui.offline.sign_in.SignInViewModelFactory
import com.fulora.app.ui.onboarding.OnboardingFragment
import com.fulora.app.ui.onboarding.OnboardingViewModel
import com.fulora.app.ui.onboarding.OnboardingViewModelFactory
import com.fulora.app.ui.splash_screen.SplashScreenFragment
import com.fulora.app.ui.splash_screen.SplashScreenViewModel
import com.fulora.app.ui.splash_screen.SplashScreenViewModelFactory

object Injector {
    fun provideFuloraDatabase(context: Context): FuloraDatabase {
        return FuloraDatabase.getInstance(context)
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository(provideFuloraDatabase(context))
    }

    fun providePlantingAreaRepository(context: Context): PlantingAreaRepository {
        return PlantingAreaRepository(provideFuloraDatabase(context))
    }

    fun providePlantRepository(context: Context): PlantRepository {
        return PlantRepository(provideFuloraDatabase(context))
    }

    fun provideHomeViewModel(context: Context): HomeViewModel {
        return HomeViewModelFactory(providePlantingAreaUseCase(context)).create(HomeViewModel::class.java)
    }

    fun providePlantingAreaUseCase(context: Context) : PlantingAreaUseCase {
        return PlantingAreaUseCase(providePlantingAreaRepository(context))
    }

    fun providerSplashScreenViewModel(fragment: SplashScreenFragment): SplashScreenViewModel {
        return SplashScreenViewModelFactory(fragment).create(SplashScreenViewModel::class.java)
    }

    fun provideOnboardingViewModel(fragment: OnboardingFragment): OnboardingViewModel {
        return OnboardingViewModelFactory(fragment).create(OnboardingViewModel::class.java)
    }

    fun provideSignInViewModel(fragment: SignInFragment): SignInViewModel {
        return SignInViewModelFactory(fragment).create(SignInViewModel::class.java)
    }
}