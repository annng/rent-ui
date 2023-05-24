package com.rent.app.injection

import android.app.Application
import com.crocodic.core.annotation.AppScope
import com.crocodic.core.injection.ContextModule
import com.crocodic.core.injection.CoreComponent
import com.crocodic.core.injection.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication


@AppScope
@Component(
    modules = [
        ContextModule::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [
        CoreComponent::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent

    }
}