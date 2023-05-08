package com.magic.officeapp.di

import com.magic.officeapp.data.api.ApiAttendanceInterface
import com.magic.officeapp.data.api.ApiAuthInterface
import com.magic.officeapp.data.api.ApiEmployeeInterface
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
        return Retrofit.Builder().baseUrl("http://159.89.203.253:1337/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun apiAuth(retrofit: Retrofit): ApiAuthInterface {
        return retrofit.create(ApiAuthInterface::class.java)
    }

    @Provides
    @Singleton
    fun apiAttendance(retrofit: Retrofit): ApiAttendanceInterface {
        return retrofit.create(ApiAttendanceInterface::class.java)
    }

    @Provides
    @Singleton
    fun apiEmployee(retrofit: Retrofit): ApiEmployeeInterface {
        return retrofit.create(ApiEmployeeInterface::class.java)
    }


}