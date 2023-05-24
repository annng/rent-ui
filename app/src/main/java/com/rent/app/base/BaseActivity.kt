package com.rent.app.base

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.crocodic.core.base.CoreActivity
import com.google.android.material.snackbar.Snackbar
import com.rent.app.R
import com.rent.app.util.ext.hide
import com.rent.app.util.ext.show
import com.rent.app.util.helper.dialog.DialogHelper
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.dialog_custom.view.*

open class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : CoreActivity<B, VM>() {

    lateinit var loading: DialogHelper
    lateinit var dialog: DialogHelper

    internal var toolbar: Toolbar? = null
    lateinit var actionBar: ActionBar
    lateinit var title: TextView

    var isLoading = false
    var offset = 0
    var isSetData = false
    lateinit var searchView: SearchView

    //Content State
    internal var progressBar: AVLoadingIndicatorView? = null
    internal var ivNotFound: ImageView? = null
    internal var tvReload: TextView? = null
    internal var tvSubReload: TextView? = null
    internal var buttonReload: Button? = null
    protected var flContentLoading: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loading = DialogHelper(this, true)
        dialog = DialogHelper(this)


    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        try {
            toolbar = findViewById(R.id.toolbar)
            setupToolbar()
        } catch (e: NullPointerException) {

        }

        initState()
    }

    /**
     * intialization layout state
     * for error, loading state
     * implement on every page that in xml include `content_loading_view` before use [showErrorStateView]
     */
    fun initState() {

        try {
            progressBar = findViewById(R.id.progressBar)
            ivNotFound = findViewById(R.id.ivNotFound)
            tvReload = findViewById(R.id.tvReload)
            tvSubReload = findViewById(R.id.tvSubReload)
            buttonReload = findViewById(R.id.buttonRetry)
            flContentLoading = findViewById(R.id.flContentLoading)

            hideAllLoadingStateView()
        } catch (e: Exception) {

        }
    }

    /**
     * hide all state info ex: progressbar dan image state
     */
    protected fun hideAllLoadingStateView() {
        progressBar?.visibility = View.GONE
        tvReload?.visibility = View.GONE
        tvSubReload?.visibility = View.GONE
        ivNotFound?.visibility = View.GONE
        buttonReload?.visibility = View.GONE

        flContentLoading?.visibility = View.GONE
        flContentLoading?.isClickable = false
        flContentLoading?.isEnabled = false
    }

    /**
     * show error state
     * [title] title for info error state
     * [message] message for info error state
     * [strButton] text of button state
     * [clicked] callback to when button is clicked
     */
    protected fun showErrorStateView(
        title: String? = null,
        message: String,
        strButton: String = "",
        clicked: () -> Unit = {}
    ) {
        progressBar!!.visibility = View.GONE
        tvReload!!.visibility = View.VISIBLE
        tvSubReload!!.visibility = View.VISIBLE
        ivNotFound!!.visibility = View.VISIBLE
        if (strButton.isEmpty())
            buttonReload!!.hide()
        else
            buttonReload!!.show()

        if (title.isNullOrEmpty())
            tvReload!!.hide()
        else
            tvReload!!.text = title

        tvSubReload!!.text = message
        ivNotFound!!.setImageResource(R.drawable.img_state_no_data)

        buttonReload!!.setOnClickListener {
            clicked()
        }
    }

    override fun initListener() {

    }


    /**
     * collect all variable / function related with view
     * ex : adapter, listener, binding
     */
    override fun initView() {

    }

    override fun initStateView() {

    }

    /**
     * processing result from viewModel to activity / fragment
     */
    override fun observeData() {

    }

    override fun onFirstLoad() {

    }

    override fun onRefresh() {

    }

    override fun onLoadFinish() {

    }

    override fun showContentView() {

    }

    override fun initBind() {

    }

    /**
     * setup toolbar widget
     * icon back and toolbar text color
     */
    protected fun setupToolbar() {
        toolbar?.let {
            setSupportActionBar(it)
            actionBar = supportActionBar!!

            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            it.setTitleTextColor(ContextCompat.getColor(this, R.color.black))

            val viewActionBar = layoutInflater.inflate(R.layout.toolbar, null)
            //Center the textview in the ActionBar
            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER_VERTICAL or Gravity.END
            )

            actionBar = supportActionBar!!
            actionBar.setCustomView(viewActionBar, params)
            actionBar.setDisplayShowCustomEnabled(false)
            actionBar.setDisplayShowTitleEnabled(false)
        }
    }

    protected fun setToolbar(idResDrawable: Int) {
        setToolbar(idResDrawable, false)
    }

    fun setToolbar(idResDrawable: Int, isBack: Boolean) {
        if (isBack) {
            actionBar.setHomeAsUpIndicator(idResDrawable)
        }
        actionBar.setDisplayHomeAsUpEnabled(isBack)
        actionBar.setDisplayShowCustomEnabled(true)


    }

    /**
     * setting back button resource, title and show / hide the back button
     * [idResDrawable] resource for icon back button
     * [strTitle] title on toolbar
     * [isBack] show / hide icon [idResDrawable]
     */
    fun setToolbar(idResDrawable: Int, strTitle: String, isBack: Boolean = true) {
        if (isBack) {
            actionBar.setHomeAsUpIndicator(idResDrawable)
        }
        actionBar.setDisplayHomeAsUpEnabled(isBack)
        actionBar.setDisplayShowCustomEnabled(true)

        actionBar.title = strTitle
    }

    fun setToolbar(strTitle: String, isBack: Boolean = true) {
        if (isBack) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_back)
        }
        actionBar.setDisplayHomeAsUpEnabled(isBack)
        actionBar.setDisplayShowCustomEnabled(true)
        toolbar?.title = strTitle

    }


    /**
     * validate form is empty with error state below [editText]
     */
    protected fun isFill(vararg editText: EditText): Boolean {
        for (e in editText) {
            if (e.text.isEmpty()) {
                e.setError(getString(R.string.field_error_empty_field))
                e.requestFocus()
                return false
            }
        }

        return true
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            getViewBack()?.id -> {
                onBackPressed()
            }
        }
    }

    /**
     * show dialog for error state with only button positive
     */
    protected fun dialogError(
        strTitle: String,
        strContent: String,
        positive: () -> Unit = { dialog.dismiss() },
    ) {
        dialog.dialogCustom(R.layout.dialog_custom) { view ->
            view.title.text = strTitle
            view.subContent.text = strContent

            view.positive.text = getString(R.string.action_ok)
            view.positive.setOnClickListener {
                positive()
            }

            view.negative.hide()
        }

        dialog.show()
    }

    /**
     * show dialog for confirmation state with positive and negative button for action
     */
    protected fun dialogConfirmation(
        strTitle: String,
        strContent: String,
        positive: () -> Unit = { dialog.dismiss() },
        negative: () -> Unit = { dialog.dismiss() }
    ) {
        dialog.dialogCustom(R.layout.dialog_custom) { view ->
            view.title.text = strTitle
            view.content.text = strContent

            view.positive.setOnClickListener {
                positive()
            }

            view.negative.setOnClickListener {
                negative()
            }
        }

        dialog?.show()
    }


    protected fun dialogAlert(
        strTitle: String,
        strContent: String,
        positive: () -> Unit
    ) {
        dialog.dialogCustom(R.layout.dialog_custom) { view ->
            view.title.text = strTitle
            view.content.text = strContent

            view.positive.setOnClickListener {
                positive()
            }
        }

        dialog.show()
    }

    /**
     * show loading dialog
     */
    protected fun dialogLoading() {
        loading.dialogCustom(R.layout.dialog_loading) { view ->

        }

        loading.show()
    }

    fun snackbar(view: View, message: String = "") {
        val snackbar = Snackbar.make(
            view, message,
            Snackbar.LENGTH_LONG
        )
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        snackbar.show()
    }


}
