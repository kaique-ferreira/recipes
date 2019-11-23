package com.aruana.recipes.di

import com.aruana.recipes.networking.RecipesService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideRecipesService(): RecipesService = Retrofit.Builder()
            .baseUrl(RecipesService.API_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(RecipesService::class.java)
}