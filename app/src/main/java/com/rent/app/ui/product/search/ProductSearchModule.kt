package com.rent.app.ui.product.search

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductSearchModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductSearchViewModel::class)
    abstract fun bindViewModel(viewModel: ProductSearchViewModel): ViewModel
}