package com.magic.officeapp.di

import com.magic.officeapp.data.api.ApiAuthInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun apiAuth(retrofit: Retrofit): ApiAuthInterface {
        return retrofit.create(ApiAuthInterface::class.java)
    }


}