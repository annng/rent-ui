package com.crocodic.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class CoreViewModel : ViewModel(){

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val logoutState = MutableLiveData<Boolean>()
    val authToken = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}