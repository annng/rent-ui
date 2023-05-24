package com.rent.app.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.crocodic.core.base.CoreViewModel
import com.crocodic.core.helper.SessionHelper
import javax.inject.Inject

open class BaseViewModel : CoreViewModel(), LifecycleObserver {


    @Inject
    lateinit var sessionHelper: SessionHelper

}