package com.rent.app.ui.auth.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rent.app.R
import com.rent.app.base.BaseViewModel
import javax.inject.Inject


open class LoginViewModel @Inject constructor(
    private val context: Application
) : BaseViewModel() {

    private var strUserName = ""
    private var strPassword = ""

    /**
     * mendapatkan token fcm
     */
    fun updateFcmTokenId() {
//        MyFirebaseMessagingService.getTokenFCM(session)
    }

    /**
     * Validasi login sebelum mendapatkan token
     *
     * @param userName user name yang dimasukkan saat login
     * @param pass password yang dimasukkan saat login
     */
    fun loginValidation(userName: String, pass: String) {
        if (userName.isEmpty()) {
            val message = context.getString(R.string.toast_error_invalid_email)

            return
        }else if(pass.isEmpty()){
            val message = context.getString(R.string.toast_error_password_doesnt_match)

            return
        }
        strUserName = userName
        strPassword = pass
        login(strUserName, strPassword)
    }

    /**
     * Melakukan otentikasi login dengan server side
     *
     * @param userName user name yang dimasukkan saat login
     * @param pass password name yang dimasukkan saat login
     */
    val obsLogin = MutableLiveData<Boolean>()
    fun login(userName: String, pass: String) {
        obsLogin.postValue(true)
    }
}