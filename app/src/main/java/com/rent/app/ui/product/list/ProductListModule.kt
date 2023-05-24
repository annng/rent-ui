package com.rent.app.ui.product.list

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductListModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindViewModel(viewModel: ProductListViewModel): ViewModel
}