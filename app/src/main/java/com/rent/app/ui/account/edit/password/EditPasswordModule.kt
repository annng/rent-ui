package com.rent.app.ui.account.edit.password

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EditPasswordModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditPasswordViewModel::class)
    abstract fun bindViewModel(viewModel: EditPasswordViewModel): ViewModel
}