package com.rent.app.ui.auth.login

import android.content.Intent
import android.os.Bundle
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.util.Constants
import com.rent.app.databinding.ActivityUserLoginBinding
import com.rent.app.ui.auth.register.RegisterActivity
import com.rent.app.ui.main.MainActivity

class LoginActivity : BaseActivity<ActivityUserLoginBinding, LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_user_login)

        initView()
    }

    override fun initView() {
        super.initView()
        initBind()
    }

    override fun initBind() {
        super.initBind()
        binding.context = this
    }

    fun gotoHome(){
        val i = Intent(this, MainActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }

    fun gotoRegister(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }
}