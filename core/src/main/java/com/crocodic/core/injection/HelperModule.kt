package com.crocodic.core.injection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.crocodic.core.helper.SessionHelper
import com.crocodic.core.helper.ToastHelper
import dagger.Module
import dagger.Provides

@Module
class HelperModule {

    @Provides
    fun provideToastHelper(application: Application) = ToastHelper(application)




}
