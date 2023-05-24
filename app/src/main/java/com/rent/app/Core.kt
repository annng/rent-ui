package com.rent.app

import com.crocodic.core.helper.Log
import com.crocodic.core.injection.DaggerCoreComponent
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.rent.app.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


open class Core : DaggerApplication() {

    lateinit var mFirebaseAnalytics: FirebaseAnalytics


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val coreComponent = DaggerCoreComponent.builder().create(this).build()
        return DaggerAppComponent.builder()
            .create(this)
            .coreComponent(coreComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Log.logger = object : Log.ConCreateLog() {
            override val tag: String
                get() = "Package App"
            override val isDebug: Boolean
                get() = true
        }

        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }




}