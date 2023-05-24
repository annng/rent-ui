package com.rent.app.util.ext

import java.text.DecimalFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

private var pattern: Pattern? = null
private var matcher: Matcher? = null

//Email Pattern
private val EMAIL_PATTERN =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[getA-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

private val PHONE_PATTERN = "^[0-9-+]{10,15}$"
private val PASSWORD_PATTERN = "^(?=.*\\d).{8}$"

fun String.validateEmail(): Boolean {
    pattern = Pattern.compile(EMAIL_PATTERN)
    matcher = pattern!!.matcher(this)
    return matcher!!.matches()
}

fun String.validatePhone(): Boolean {
    if (this.startsWith("08") ||
        this.startsWith("+62") ||
        this.startsWith("62")
    ) {
        pattern = Pattern.compile(PHONE_PATTERN)
        matcher = pattern!!.matcher(this)
        return matcher!!.matches()
    }

    return false
}

fun String.validatePassword(): Boolean {
    val REGEX_DIGIT = ".*[0-9].*".toRegex()
    val REGEX_MIN_SIX_CHAR = ".{8,}".toRegex()
    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern!!.matcher(this)
    return matcher!!.matches()
}

fun matchPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

fun String.parseInt(): Int {
    if (this.isNullOrEmpty())
        return 0
    else {
        try {
            return this.toInt()
        } catch (e: NumberFormatException) {
            return 0
        }
    }
}

fun String.parseDouble(): Double {
    val numeric = this.replace(".","").replace(",", ".")
    if (numeric.isNullOrEmpty())
        return 0.0
    else {
        try {
            return numeric.toDouble()
        } catch (e: NumberFormatException) {
            return 0.0
        }
    }
}

fun String.getName() : String{
    return this.substring(this.lastIndexOf("/"))
}

fun String.capitalizeFirstLetter() = this.split(" ").joinToString(" ") { it.capitalize() }.trimEnd()

fun Long.priceFormat() : String{
    val d_price = this.toDouble()

    val formatter = DecimalFormat("#,###.##")

    return formatter.format(d_price)
}

fun Int.asString() : String{
    return this.toString()
}