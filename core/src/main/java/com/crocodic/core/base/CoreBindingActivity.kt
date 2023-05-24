package com.crocodic.core.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.helper.SessionHelper
import com.crocodic.core.helper.ToastHelper
import com.crocodic.core.listener.CoreListener
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject


/**
 * Created by Android Studio.
 * Project : crocodic_library
 * User: ridwan akbar
 * Email: ridwansantos23@gmail.com
 * Telegram : @Ridwan_23
 * Date: 11/16/20
 * Time: 3:15 PM
 */
abstract class CoreBindingActivity<B : ViewDataBinding> : DaggerAppCompatActivity(),
    CoreListener, View.OnClickListener {

    @Inject
    lateinit var sessionHelper: SessionHelper

    @Inject
    lateinit var toastHelper: ToastHelper

    protected lateinit var binding: B

    override fun setToolbarTitle(title: String?) {
        getViewToolbarTitle()?.text = title
    }

    override fun getViewLogout(): ImageView? {
        val resID = resources.getIdentifier("ivLogout", "id", packageName)
        return findViewById(resID)
    }

    override fun getViewBack(): ImageView? {
        val resID = resources.getIdentifier("ivToolbarBack", "id", packageName)
        return findViewById(resID)
    }

    override fun getViewHome(): ImageView? {
        val resID = resources.getIdentifier("ivHome", "id", packageName)
        return findViewById(resID)
    }

    override fun getViewToolbarTitle(): TextView? {
        val resID = resources.getIdentifier("tvToolbarTitle", "id", packageName)
        return findViewById(resID)
    }


    override fun readFromIntent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setLayoutRes(layoutResID: Int){
        binding = DataBindingUtil.setContentView(this, layoutResID)
    }

    @Deprecated("gunakan setLayoutRes",ReplaceWith("setLayoutRes(layoutResID)"))
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

}