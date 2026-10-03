package com.dgomesdev.littlelemonexercise

import android.app.Application
import com.dgomesdev.littlelemonexercise.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LittleLemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LittleLemonApplication)
            modules(appModule)
        }
    }
}
