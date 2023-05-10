package com.magic.officeapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.magic.officeapp.data.api.ApiAnnouncementInterface
import com.magic.officeapp.data.api.ApiAttendanceInterface
import com.magic.officeapp.data.api.ApiAuthInterface
import com.magic.officeapp.data.api.ApiEmployeeInterface
import com.magic.officeapp.data.repository.AnnouncementRepository
import com.magic.officeapp.data.repository.AttendanceRepository
import com.magic.officeapp.data.repository.AuthRepository
import com.magic.officeapp.data.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        @ApplicationContext context: Context, ApiAuthInterface: ApiAuthInterface
    ): AuthRepository {
        return AuthRepository(context.userDataStore, ApiAuthInterface)
    }

    @Provides
    @Singleton
    fun provideAttendanceRepository(
        apiAttendanceInterface: ApiAttendanceInterface
    ): AttendanceRepository {
        return AttendanceRepository(apiAttendanceInterface)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(
        ApiEmployeeInterface: ApiEmployeeInterface
    ): EmployeeRepository {
        return EmployeeRepository(ApiEmployeeInterface)
    }

    @Provides
    @Singleton
    fun provideAnnouncementRepository(
        ApiAnnouncementInterface: ApiAnnouncementInterface
    ): AnnouncementRepository {
        return AnnouncementRepository(ApiAnnouncementInterface)
    }
}