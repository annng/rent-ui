package com.rent.app.ui.account.edit.profile

import androidx.lifecycle.ViewModel
import com.crocodic.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EditProfileModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindViewModel(viewModel: EditProfileViewModel): ViewModel
}