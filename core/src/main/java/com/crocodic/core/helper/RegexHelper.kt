package com.crocodic.core.helper

import android.text.TextUtils
import android.util.Patterns

class RegexHelper {
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}