package com.rent.app.ui.shipping

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ShippingModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShippingViewModel::class)
    abstract fun bindViewModel(viewModel: ShippingViewModel): ViewModel
}