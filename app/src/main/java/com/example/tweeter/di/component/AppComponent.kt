package com.example.tweeter.di.component

import com.example.tweeter.app.AppPreferencesHelper
import com.example.tweeter.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[
    AppModule::class
])
interface AppComponent {
    fun appPreferencesHelper() : AppPreferencesHelper

}