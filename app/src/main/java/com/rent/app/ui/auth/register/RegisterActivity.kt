package com.rent.app.ui.auth.register

import android.os.Bundle
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.databinding.ActivityUserRegisterBinding

class RegisterActivity : BaseActivity<ActivityUserRegisterBinding, RegisterViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_user_register)
    }
}