package com.rent.app.ui.home.message

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MessageModule {
    @Binds
    @IntoMap
    @ViewModelKey(MessageViewModel::class)
    abstract fun bindViewModel(viewModel: MessageViewModel): ViewModel
}