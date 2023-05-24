package com.rent.app.injection

import android.content.Context
import com.crocodic.core.annotation.AppScope
import com.crocodic.core.helper.SessionHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideCustomSession(context: Context): SessionHelper {
        val sessionHelper = SessionHelper(context)
        sessionHelper.setName("session")
        return sessionHelper
    }


}
