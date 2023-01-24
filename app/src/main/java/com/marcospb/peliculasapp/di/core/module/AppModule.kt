package com.marcospb.peliculasapp.di.core.module

import com.marcospb.data.utils.DispacherProviderImp
import com.marcospb.data.utils.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesDispacherProvider(): DispatchersProvider {
        return DispacherProviderImp
    }

}