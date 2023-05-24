package com.rent.app.ui.main.child.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.Account
import com.rent.app.data.Menu
import com.rent.app.databinding.FragmentAkunBinding
import com.rent.app.ui.account.edit.password.EditPasswordActivity
import com.rent.app.ui.account.edit.profile.EditProfileActivity
import com.rent.app.ui.account.wishlist.WishlistActivity
import com.rent.app.util.Constants
import com.rent.app.util.ext.initItem

class AccountFragment : BaseFragment<FragmentAkunBinding, AccountViewModel>(),
    View.OnClickListener {

    var menus = Menu.getMenuAccount()
    lateinit var adapter : AccountAdapter

    var account = Account.getAccount()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindView(inflater, R.layout.fragment_akun, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        super.initView()
        initListener()
        initAdapter()
        initBinding()
    }

    override fun initBinding() {
        super.initBinding()
        binding.account = account
        binding.fragment = this
    }

    override fun initAdapter() {
        super.initAdapter()
        adapter = AccountAdapter(menus, 3){
            when(menus[it].resTitle){
                R.string.title_menu_item_favorit -> {
                    val i = Intent(requireContext(), WishlistActivity::class.java)
                    startActivityForResult(i, Constants.INTENT.REQ_INTENT)
                }
                R.string.title_menu_edit_password -> {
                    val i = Intent(requireContext(), EditPasswordActivity::class.java)
                    startActivityForResult(i, Constants.INTENT.REQ_INTENT)
                }
            }
        }
        binding.rvMenuAkun.initItem(requireActivity(), adapter)
    }

    override fun initListener() {
        super.initListener()
        binding.ivPhotoProfile.setOnClickListener(this)
    }

    fun gotoEditProfile(){
        val i = Intent(requireContext(), EditProfileActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }

    fun gotoEditPasword(){
        val i = Intent(requireContext(), EditPasswordActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }


    override fun onClick(v: View?) {
        when(v){
            binding.ivPhotoProfile -> ToastHelper(requireContext()).showAToast("Edit Photo / View Photo")
        }
    }


}