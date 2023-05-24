package com.crocodic.widget.currency

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.crocodic.widget.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.util.*
import kotlin.math.pow


/**
 * Created by bulent.turkmen on 8/9/2016.
 */
class CurrencyEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    /**
     * add attrs in values to your project
     * <yourpackagename.CurrencyEditText app:locale="in_ID" app:showSymbol="false" android:inputType="numberDecimal"></yourpackagename.CurrencyEditText>
     * ------------------------------------------------
     * Java :
     * CurrencyEditText whatEver;
     * Method to get Value from CurrencyEditText
     * - getCurrencyDouble
     * - getCurrencyText
     * - getCurrencyTextWithoutDivider
     */
    private var mGroupDivider = 0.toChar()
    private var mMonetaryDivider = 0.toChar()
    private var mLocale = ""
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
                Log.e(CurrencyEditText::class.java.canonicalName, e.message.toString())
                locale = defaultLocale
            }
        }
    }

    private val defaultLocale: Locale
        private get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) context.resources.configuration.locales.get(
            0
        ) else context.resources.configuration.locale

    /***
     * It resets text currently displayed If user changes separators or locale etc.
     */
    private fun resetText() {
        var s: String = text.toString()
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
            Log.e(CurrencyEditText::class.java.canonicalName, e.message.toString())
        }
    }

    private val onTextChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isEmpty()) return
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
                Log.e(javaClass.canonicalName, e.message.toString())
            }
            val currency = text.replace(",", "").replace(".", "")
            val formatter = DecimalFormat("#,###")
            var label = ""
            try {
                val harga = formatter.format(currency.toDouble())
                label = harga.replace(",", ".")
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            setText(label)
            try {
                setSelection(label.length)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            addTextChangedListener(this)
        }

        override fun afterTextChanged(s: Editable) {}
    }


    private fun format(text: String): String {
        return if (mShowSymbol) try {
            numberFormat!!.format(text.toDouble() / 10.0.pow(fractionDigit.toDouble()))
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            ""
        } else try {
            numberFormat!!.format(text.toDouble() / 10.0.pow(fractionDigit.toDouble()))
                .replace(currencySymbol!!, "")
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            ""
        }
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
            var text: String = text.toString()
            if (text.isEmpty()) return 0.0
            text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                .replace(".", "").replace(" ", "")
                .replace(currencySymbol!!, "").trim { it <= ' ' }
            return if (showSymbol()) text.replace(currencySymbol!!, "").toDouble() / Math.pow(
                10.0,
                fractionDigit.toDouble()
            ) else text.toDouble()
        }

    /**
     * @return String value for current text
     */
    val currencyText: String
        get() = if (showSymbol()) text.toString()
            .replace(currencySymbol ?: "", "") else text.toString()

    val currencyTextWithoutDivider: String
        get() = if (showSymbol()) text.toString().replace(currencySymbol ?: "", "")
            .replace(".", "") else text.toString().replace(".", "").replace(",", "")

    fun setTextValue(s: CharSequence) {
        var text = s.toString()
        text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
            .replace(".", "").replace(" ", "")
            .replace(currencySymbol!!, "").trim { it <= ' ' }
        try {
            text = format(text)
        } catch (e: ParseException) {
            Log.e(CurrencyEditText::class.java.canonicalName, e.message.toString())
        }
        val currency = text.replace(",", "").replace(".", "")
        val formatter = DecimalFormat("#,###")
        var label = ""
        try {
            val harga = formatter.format(currency.toDouble())
            label = harga.replace(",", ".")
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        setText(label)
        try {
            setSelection(label.length)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

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
                defaultLocale else mLocale =
                a.getString(R.styleable.currencyEditText_locale)!!
            if (a.getString(R.styleable.currencyEditText_showSymbol) != null) mShowSymbol =
                a.getBoolean(R.styleable.currencyEditText_showSymbol, false)
            if (mLocale == "") {
                locale = defaultLocale
            } else {
                if (mLocale.contains("-")) mLocale = mLocale.replace("-", "_")
                val l = mLocale.split("_".toRegex()).toTypedArray()
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
        this.addTextChangedListener(onTextChangeListener)
    }
}