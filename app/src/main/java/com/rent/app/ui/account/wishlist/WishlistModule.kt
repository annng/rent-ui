package com.rent.app.ui.account.wishlist

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WishlistModule {
    @Binds
    @IntoMap
    @ViewModelKey(WishlistViewModel::class)
    abstract fun bindViewModel(viewModel: WishlistViewModel): ViewModel
}