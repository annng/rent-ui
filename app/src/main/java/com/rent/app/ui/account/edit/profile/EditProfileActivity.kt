package com.rent.app.ui.account.edit.profile

import android.os.Bundle
import android.view.MenuItem
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.databinding.ActivityEditProfileBinding
import com.rent.app.util.helper.dialog.DialogList

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding, EditProfileViewModel>() {

    var genders = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_edit_profile)
        setToolbar(getString(R.string.toolbar_edit_profil))

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

    fun dialogGender() {
        genders.clear()
        genders.add(getString(R.string.label_gender_male))
        genders.add(getString(R.string.label_gender_female))
        DialogList(this).show(getString(R.string.label_jenis_kelamin), genders){
            binding.tvJenisKelamin.text = genders[it]
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}