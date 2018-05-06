package com.example.tweeter.di.module

import android.app.Application
import android.content.Context
import com.example.tweeter.R
import com.example.tweeter.app.AppPreferencesHelper
import com.example.tweeter.util.security.ObscuredSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class  AppModule {
    val mApplication: Application

    constructor(application: Application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    fun provideApplicationContext() = mApplication

    @Provides
    @Singleton
    fun provideObscuredSharedPreferences()
            = ObscuredSharedPreferences(this.mApplication, mApplication.getSharedPreferences(mApplication.getString(R.string.app_name), Context.MODE_PRIVATE))

    @Provides
    @Singleton
    fun provideAppPreferences(sharedPreferences: ObscuredSharedPreferences)
            = AppPreferencesHelper(sharedPreferences)
}