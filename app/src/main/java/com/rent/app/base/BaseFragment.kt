package com.rent.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.crocodic.core.helper.SessionHelper
import com.google.android.material.snackbar.Snackbar
import com.rent.app.R
import com.rent.app.util.ext.hide
import com.rent.app.util.ext.show
import com.rent.app.util.helper.dialog.DialogHelper
import com.wang.avi.AVLoadingIndicatorView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.content_loading_view.view.*
import kotlinx.android.synthetic.main.dialog_custom.view.*
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class

BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : DaggerFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: VM
    protected lateinit var binding: B

    @Inject
    lateinit var session: SessionHelper

    lateinit var loading: DialogHelper

    //Content State
    internal var progressBar: AVLoadingIndicatorView? = null
    internal var ivNotFound: ImageView? = null
    internal var tvReload: TextView? = null
    internal var tvSubReload: TextView? = null
    internal var buttonReload: Button? = null
    protected var flContentLoading: FrameLayout? = null

    //dialog
    lateinit var dialogAlert: DialogHelper

    var offset = 0
    var keyword = ""
    var isLoading = false
    var isSetData = true

    /**
     * @return true if child activity should use mData binding instead of [.setContentView]
     */
    protected open fun shouldUseDataBinding(): Boolean {
        return true
    }

    open fun initBinding() {

    }

    /**
     * Semua event listener di inisialisasikan pada method initListener()
     */
    open fun initListener() {}

    /**
     * state untuk menampilkan status loading, empty, error
     *
     */
    open fun initViewState() {}

    /**
     * inisialisasi layout state
     * untuk error, loading
     */
    fun initState(vi: View) {
        try {
            progressBar = vi.findViewById(R.id.progressBar)
            ivNotFound = vi.findViewById(R.id.ivNotFound)
            tvReload = vi.findViewById(R.id.tvReload)
            tvSubReload = vi.findViewById(R.id.tvSubReload)
            buttonReload = vi.findViewById(R.id.buttonRetry)
            flContentLoading = vi.findViewById(R.id.flContentLoading)

            hideAllLoadingStateView()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * menampilkan progress bar loading
     */
    fun showLoadingStateView() {
        flContentLoading?.visibility = View.VISIBLE
        flContentLoading?.isClickable = true
        flContentLoading?.isEnabled = true

        progressBar?.show()
        progressBar?.animate()
        tvReload?.visibility = View.GONE
        tvSubReload?.visibility = View.GONE
        ivNotFound?.visibility = View.GONE
        buttonReload?.visibility = View.GONE
    }


    /**
     * menyembunyikan semua state seperti progressbar dan gambar state
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
     * menampilkan state error dengan judul dan pesan
     * [title] teks yang ditampilkan sebagai judul state
     * [message] teks yang ditampilkan sebagai pesan state
     */
    protected fun showErrorStateView(title: String? = null, message: String) {
        progressBar!!.visibility = View.GONE
        tvReload!!.visibility = View.VISIBLE
        tvSubReload!!.visibility = View.VISIBLE
        ivNotFound!!.visibility = View.VISIBLE
        buttonReload!!.visibility = View.GONE
        if (title.isNullOrEmpty())
            tvReload!!.hide()
        else
            tvReload!!.text = title

        tvSubReload!!.text = message
        flContentLoading!!.show()
        ivNotFound!!.setImageResource(R.drawable.img_state_no_data)
    }

    /**
     * menampilkan state error dengan judul dan pesan
     * [title] teks yang ditampilkan sebagai judul state
     * [message] teks yang ditampilkan sebagai pesan state
     */
    protected fun showErrorStateView(
        title: String? = null, message: String, resDrawable: Int = R.drawable.img_state_no_data,
        btnTitle: String = getString(R.string.action_ok), listener: () -> Unit
    ) {
        progressBar!!.visibility = View.GONE
        tvReload!!.visibility = View.VISIBLE
        tvSubReload!!.visibility = View.VISIBLE
        ivNotFound!!.visibility = View.VISIBLE
        buttonReload!!.visibility = View.VISIBLE
        buttonReload!!.text = btnTitle

        tvReload!!.text = title
        tvSubReload!!.text = message
        flContentLoading!!.show()
        ivNotFound!!.setImageResource(resDrawable)
        buttonReload!!.setOnClickListener { listener() }
    }

    /**
     * Semua view di inisialisasikan pada method initView()
     */
    open fun initView() {}
    open fun initAdapter() {}

    protected fun bindView(
        inflater: LayoutInflater,
        layoutResID: Int,
        container: ViewGroup?
    ): View {

        return if (shouldUseDataBinding()) {
            binding = DataBindingUtil.inflate(inflater, layoutResID, container, false)
            binding.root
        } else {
            inflater.inflate(layoutResID, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = DialogHelper(requireActivity(), true)

        val viewModelClass = (javaClass
            .genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM> // 0 is BaseViewModel

        dialogAlert = DialogHelper(requireActivity(), false)

        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)

    }

    protected fun dialogAlert(
        strTitle: String? = null,
        resImg: Int? = null,
        positive: () -> Unit,
        negative: () -> Unit
    ) {

        dialogAlert.dialogCustom(R.layout.dialog_custom) {
            if (strTitle.isNullOrEmpty()) {
                it.tvReload.hide()
            } else {
                it.tvReload.show()
                it.tvReload.text = strTitle
            }

            if (resImg == null) {
                it.ivNotFound.hide()
            } else {
                it.ivNotFound.show()
                it.ivNotFound.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        resImg
                    )
                )
            }

            it.positive.setOnClickListener {
                dialogAlert.dismiss()
                positive()
            }

            it.negative.setOnClickListener {
                dialogAlert.dismiss()
                negative()
            }
        }

        dialogAlert.show()
    }


    protected fun dialogLoading(strMessage: String = getString(R.string.label_dialog_loading)) {
        loading.dialogCustom(R.layout.dialog_loading) { view ->
            view.tvReload.text = strMessage
        }

        loading.show()
    }

    fun dialogSuccess(
        strTitle: String? = null,
        resImg: Int? = null,
        positive: () -> Unit
    ) {
        val dialog = DialogHelper(requireContext(), true)
        dialog.dialogCustom(R.layout.dialog_custom) {
            if (strTitle.isNullOrEmpty()) {
                it.tvReload.hide()
            } else {
                it.tvReload.show()
                it.tvReload.text = strTitle
            }

            if (resImg == null) {
                it.ivNotFound.hide()
            } else {
                it.ivNotFound.show()
                it.ivNotFound.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        resImg
                    )
                )
            }

            it.positive.text = getString(R.string.action_ok)
            it.positive.setOnClickListener {
                dialog.dismiss()
                positive()
            }
        }

        dialog.show()

    }

    fun snacked(view: View, message: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, message, duration).show()
    }

    fun snacked(view: View, message: String?, duration: Int = Snackbar.LENGTH_SHORT) {
        message?.let { Snackbar.make(view, it, duration).show() }
    }

    fun isFill(vararg editText: EditText): Boolean {
        for (e in editText) {
            if (e.text.isEmpty()) {
                e.error = getString(R.string.field_error_empty_field)
                return false
            }
        }

        return true
    }


    fun isFill(vararg spinner: AppCompatSpinner): Boolean {
        for (e in spinner) {
            if (e.selectedItemPosition == 0) {
                e.requestFocus()
                e.performClick()
                return false
            }
        }

        return true
    }


}
