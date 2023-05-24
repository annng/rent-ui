package com.rent.app.ui.home.review

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ReviewModule {
    @Binds
    @IntoMap
    @ViewModelKey(ReviewViewModel::class)
    abstract fun bindViewModel(viewModel: ReviewViewModel): ViewModel
}