package com.crocodic.core.injection

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
