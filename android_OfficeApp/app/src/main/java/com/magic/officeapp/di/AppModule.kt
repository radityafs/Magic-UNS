package com.magic.officeapp.di

import com.magic.officeapp.data.api.*
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

    @Provides
    @Singleton
    fun apiAnnouncement(retrofit: Retrofit): ApiAnnouncementInterface {
        return retrofit.create(ApiAnnouncementInterface::class.java)
    }

    @Provides
    @Singleton
    fun apiRequest(retrofit: Retrofit): ApiRequestInterface {
        return retrofit.create(ApiRequestInterface::class.java)
    }

    @Provides
    @Singleton
    fun apiPayroll(retrofit: Retrofit): ApiPayrollInterface {
        return retrofit.create(ApiPayrollInterface::class.java)
    }

}