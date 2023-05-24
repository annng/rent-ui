package com.rent.app.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.util.Constants
import com.rent.app.databinding.ActivitySplashScreenBinding
import com.rent.app.ui.auth.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashScreenBinding, SplashViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
        lifecycle.addObserver(viewModel)

        initView()

        observeData()
    }

    override fun observeData() {
        super.observeData()

        viewModel.delayCountDown(2000)
        viewModel.splashNotifier.observe(this, { finishSplash ->
            gotoPage()
        })


    }

    private fun gotoPage() {
        val i = Intent(this, LoginActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }
}