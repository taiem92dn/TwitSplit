package com.example.tweeter.app

import android.app.Application
import com.example.tweeter.di.component.AppComponent
import com.example.tweeter.di.component.DaggerAppComponent
import com.example.tweeter.di.module.AppModule

class App : Application() {

    companion object {

        @Volatile private var INSTANCE: App? = null

        @JvmStatic
        fun getInstance(): App? =
                INSTANCE

    }

    private var mAppComponent : AppComponent? = null

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        this.mAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun getAppComponent() = mAppComponent
}