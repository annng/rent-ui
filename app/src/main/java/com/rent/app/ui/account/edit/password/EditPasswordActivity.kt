package com.rent.app.ui.account.edit.password

import android.os.Bundle
import android.view.MenuItem
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.databinding.ActivityEditPasswordBinding

class EditPasswordActivity : BaseActivity<ActivityEditPasswordBinding, EditPasswordViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_edit_password)
        setToolbar(getString(R.string.title_menu_edit_password))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}