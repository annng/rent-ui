package com.crocodic.widget.permission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.crocodic.widget.R
import com.google.android.material.snackbar.Snackbar
import com.sembozdemir.permissionskt.askPermissions
import com.sembozdemir.permissionskt.handlePermissionsResult
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity(private val activity: Activity?) : AppCompatActivity() {

    constructor() : this(null)

    private var mTitle: Int = 0
    private var mSubTitle: Int = 0
    private var mDescription: Int = 0
    private var mImage: Int = 0

    private var mPermissions: ArrayList<String>? = ArrayList()

    fun setPermission(vararg values: String): PermissionActivity {
        mPermissions?.addAll(values)
        return this
    }

    fun setImage(@DrawableRes image: Int): PermissionActivity {
        mImage = image
        return this
    }

    fun setContent(
        @StringRes title: Int,
        @StringRes subtitle: Int,
        @StringRes description: Int
    ): PermissionActivity {
        mTitle = title
        mSubTitle = subtitle
        mDescription = description
        return this
    }

    fun request(granted: () -> Unit, denied: () -> Unit) {

        mOnGranted = granted
        mOnDenied = denied

        val permissionIntent = Intent(activity, PermissionActivity::class.java).apply {
            putExtra(IMAGE, mImage)
            putExtra(TITLE, mTitle)
            putExtra(SUBTITLE, mSubTitle)
            putExtra(DESCRIPTION, mDescription)
            putStringArrayListExtra(PERMISSION, mPermissions)
        }

        activity?.startActivity(permissionIntent)
    }

    companion object {
        const val TITLE = "title"
        const val SUBTITLE = "subtitle"
        const val DESCRIPTION = "description"
        const val IMAGE = "image"
        const val PERMISSION = "permission"

        private lateinit var mOnGranted: () -> Unit
        private lateinit var mOnDenied: () -> Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        mImage = intent.getIntExtra(IMAGE, 0)
        mTitle = intent.getIntExtra(TITLE, 0)
        mSubTitle = intent.getIntExtra(SUBTITLE, 0)
        mDescription = intent.getIntExtra(DESCRIPTION, 0)
        mPermissions = intent.getStringArrayListExtra(PERMISSION)

        initView()
    }

    private fun initView() {
        if (mTitle != 0) {
            tvTitle.text = getString(mTitle)
        }
        if (mSubTitle != 0) {
            tvSubTitle.text = getString(mSubTitle)
        }
        if (mDescription != 0) {
            tvDescription.text = getString(mDescription)
        }
        if (mImage != 0) {
            ivImage.setImageResource(mImage)
        }

        btnPositive.setOnClickListener { checkPermission() }
        btnNegative.setOnClickListener {
            mOnDenied()
            onBackPressed()
        }
    }

    private fun checkPermission() {
        Toast.makeText(this, "${mPermissions?.size}", Toast.LENGTH_LONG).show()
        mPermissions?.let {
            askPermissions(*it.toTypedArray()) {
                onGranted {
                    mOnGranted()
                    finish()
                }

                onDenied {
                    mOnDenied()
                    finish()
                }

                onShowRationale { request ->
                    Snackbar.make(
                        rootView,
                        "You should grant this permission",
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction("Retry") { request.retry() }
                        .show()
                }

                onNeverAskAgain {
                    Snackbar.make(
                        rootView,
                        "You have choose never ask this permission",
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction("Setting") {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            intent.data = Uri.fromParts("package", packageName, null)
                            startActivityForResult(intent, 101)
                        }
                        .show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        handlePermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {
            checkPermission()
        }
    }
}