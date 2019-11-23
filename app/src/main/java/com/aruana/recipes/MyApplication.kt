package com.aruana.recipes

import android.app.Application
import com.aruana.recipes.di.AppComponent
import com.aruana.recipes.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}