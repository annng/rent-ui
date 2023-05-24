package com.crocodic.core.injection

import android.app.Application
import com.crocodic.core.helper.ToastHelper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HelperModule::class]
)
interface CoreComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun build(): CoreComponent
    }

    fun toastHelper(): ToastHelper
}
