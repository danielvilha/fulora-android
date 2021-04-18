package com.fulora.app.di

import android.content.Context
import com.fulora.app.home.ui.HomeViewModel
import com.fulora.app.home.ui.HomeViewModelFactory
import com.fulora.app.repositories.PlantRepository
import com.fulora.app.repositories.PlantingAreaRepository
import com.fulora.app.repositories.UserRepository
import com.fulora.app.source.local.FuloraDatabase

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
        return HomeViewModelFactory(providePlantingAreaRepository(context)).create(HomeViewModel::class.java)
    }
}