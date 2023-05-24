package com.rent.app.widget.edittext.currency

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText
import android.os.Build
import android.text.TextWatcher
import android.text.Editable
import kotlin.Throws
import android.text.InputType
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import com.rent.app.R
import java.lang.IllegalArgumentException
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.util.*

/**
 * Created by crocodic-mbp8 on 9/20/17.
 */
/**
 * Created by bulent.turkmen on 8/9/2016.
 */
class CurrencyEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    private var mGroupDivider = 0.toChar()
    private var mMonetaryDivider = 0.toChar()
    private var mLocale: String? = ""
    private var mShowSymbol = false
    private var groupDivider = 0.toChar()
    private var monetaryDivider = 0.toChar()
    private var locale: Locale? = null
    private var numberFormat: DecimalFormat? = null
    private var fractionDigit = 0
    private var currencySymbol: String? = null

    /***
     * If user does not provide a valid locale it throws IllegalArgumentException.
     *
     * If throws an IllegalArgumentException the locale sets to default locale
     */
    private fun initSettings() {
        var success = false
        while (!success) {
            try {
                fractionDigit = Currency.getInstance(locale).defaultFractionDigits
                val symbols = DecimalFormatSymbols.getInstance(locale)
                if (mGroupDivider.toInt() > 0) symbols.groupingSeparator = mGroupDivider
                groupDivider = symbols.groupingSeparator
                if (mMonetaryDivider.toInt() > 0) symbols.monetaryDecimalSeparator =
                    mMonetaryDivider
                monetaryDivider = symbols.monetaryDecimalSeparator
                currencySymbol = symbols.currencySymbol
                val df = DecimalFormat.getCurrencyInstance(locale) as DecimalFormat
                numberFormat = DecimalFormat(df.toPattern(), symbols)
                success = true
            } catch (e: IllegalArgumentException) {
                Log.e(javaClass.canonicalName, e.message!!)
                locale = defaultLocale
            }
        }
    }

    private val defaultLocale: Locale
        private get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) context.resources.configuration.locales[0] else context.resources.configuration.locale

    /***
     * It resets text currently displayed If user changes separators or locale etc.
     */
    private fun resetText() {
        var s = text.toString()
        if (s.isEmpty()) {
            initSettings()
            return
        }
        s = s.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
            .replace(".", "").replace(" ", "")
            .replace(currencySymbol!!, "").trim { it <= ' ' }
        try {
            initSettings()
            s = format(s)
            removeTextChangedListener(onTextChangeListener)
            setText(s)
            setSelection(s.length)
            addTextChangedListener(onTextChangeListener)
        } catch (e: ParseException) {
            Log.e("resetText", "oke")
            Log.e(javaClass.canonicalName, e.message!!)
        }
    }

    private val onTextChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.length == 0) return
            removeTextChangedListener(this)
            /***
             * Clear input to get clean text before format
             * '\u0020' is empty character
             */
            var text = s.toString()
            text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                .replace(".", "").replace(" ", "")
                .replace(currencySymbol!!, "").trim { it <= ' ' }
            try {
                text = format(text)
            } catch (e: ParseException) {
                Log.e(javaClass.canonicalName, e.message!!)
            }
            setText(text)
            setSelection(text.length)
            addTextChangedListener(this)
        }

        override fun afterTextChanged(s: Editable) {}
    }

    @Throws(ParseException::class)
    private fun format(text: String): String {
        return if (mShowSymbol) numberFormat!!.format(
            text.toDouble() / Math.pow(
                10.0,
                fractionDigit.toDouble()
            )
        ) else numberFormat!!.format(
            text.toDouble() / Math.pow(
                10.0,
                fractionDigit.toDouble()
            )
        ).replace(
            currencySymbol!!, ""
        )
    }

    /***
     * returns the decimal separator for current locale
     * for example; input value 1,234.56
     * returns ','
     *
     * @return decimal separator char
     */
    fun getGroupDivider(): Char {
        return groupDivider
    }

    /***
     * sets how to divide decimal value and fractions
     * for example; If you want formatting like this
     * for input value 1,234.56
     * set ','
     * @param groupDivider char
     */
    fun setGroupDivider(groupDivider: Char) {
        mGroupDivider = groupDivider
        resetText()
    }

    /***
     * returns the monetary separator for current locale
     * for example; input value 1,234.56
     * returns '.'
     *
     * @return monetary separator char
     */
    fun getMonetaryDivider(): Char {
        return monetaryDivider
    }

    /***
     * sets how to divide decimal value and fractions
     * for example; If you want formatting like this
     * for input value 1,234.56
     * set '.'
     * @param monetaryDivider char
     */
    fun setMonetaryDivider(monetaryDivider: Char) {
        mMonetaryDivider = monetaryDivider
        resetText()
    }

    /***
     *
     * @return current locale
     */
    fun getLocale(): Locale? {
        return locale
    }

    /***
     * Sets locale which desired currency format
     *
     * @param locale
     */
    fun setLocale(locale: Locale?) {
        this.locale = locale
        resetText()
    }

    /**
     * @return true if currency symbol of current locale is showing
     */
    fun showSymbol(): Boolean {
        return mShowSymbol
    }

    /***
     * Sets if currency symbol of current locale shows
     *
     * @param showSymbol
     */
    fun showSymbol(showSymbol: Boolean) {
        mShowSymbol = showSymbol
        resetText()
    }

    /**
     * @return double value for current text
     */
    val currencyDouble: Double
        get() {
            var text = text.toString()
            if (text.isEmpty()) return 0.0
            text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                .replace(".", "").replace(" ", "")
                .replace(currencySymbol!!, "").trim { it <= ' ' }
            return if (showSymbol()) text.replace(currencySymbol!!, "").toDouble() / Math.pow(
                10.0,
                fractionDigit.toDouble()
            ) else text.toDouble() / Math.pow(10.0, fractionDigit.toDouble())
        }

    /**
     * @return String value for current text
     */
    val currencyText: String
        get() = if (showSymbol()) text.toString().replace(currencySymbol!!, "") else text.toString()
    val currencyTextWithoutDivider: String
        get() = if (showSymbol()) text.toString().replace(currencySymbol!!, "")
            .replace(".", "") else text.toString().replace(".", "").replace(",", "")

    init {
        this.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.currencyEditText, 0, 0)
        try {
            if (a.getString(R.styleable.currencyEditText_groupDivider) != null) {
                mGroupDivider = a.getString(R.styleable.currencyEditText_groupDivider)!![0]
                groupDivider = mGroupDivider
            }
            if (a.getString(R.styleable.currencyEditText_monetaryDivider) != null) {
                mMonetaryDivider = a.getString(R.styleable.currencyEditText_monetaryDivider)!![0]
                monetaryDivider = mMonetaryDivider
            }
            if (a.getString(R.styleable.currencyEditText_locale) == null) locale =
                defaultLocale else mLocale = a.getString(R.styleable.currencyEditText_locale)
            if (a.getString(R.styleable.currencyEditText_showSymbol) != null) mShowSymbol =
                a.getBoolean(R.styleable.currencyEditText_showSymbol, false)
            if (mLocale == "") {
                locale = defaultLocale
            } else {
                if (mLocale!!.contains("-")) mLocale = mLocale?.replace("-", "_")
                val l = mLocale!!.split("_").toTypedArray()
                locale = if (l.size > 1) {
                    Locale(l[0], l[1])
                } else {
                    Locale("", mLocale)
                }
            }
            initSettings()
        } finally {
            a.recycle()
        }
        addTextChangedListener(onTextChangeListener)
    }
}