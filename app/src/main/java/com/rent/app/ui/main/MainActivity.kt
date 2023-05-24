package com.rent.app.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.databinding.ActivityMainBinding
import com.rent.app.ui.main.child.account.AccountFragment
import com.rent.app.ui.main.child.cart.CartFragment
import com.rent.app.ui.main.child.home.HomeFragment
import com.rent.app.ui.main.child.order.OrderFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    lateinit var currentFragment: Fragment
    lateinit var akunFragment: AccountFragment

    internal var current_position = 0
    internal var last_position = 0


    var isFocusSearch: Boolean = false
    var setPage = 0
    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_main)
        initView()

    }


    override fun initView() {
        super.initView()
        setBottomNavigation()
    }

    fun setBottomNavigation() {
        changePage(HomeFragment())


        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    changePage(HomeFragment())

                }
                R.id.buku_sewa -> {
                    changePage(OrderFragment())
                }
                R.id.troli -> {
                    changePage(CartFragment())

                }
                R.id.akun -> {
                    akunFragment = AccountFragment()
                    changePage(AccountFragment())

                }
                else -> {
                    changePage(HomeFragment())
                }
            }
            true

        }

    }


    fun changePage(classFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, classFragment)
            .commit()
        currentFragment = classFragment

    }


    fun setIsFocusSearch(isFocusSearch: Boolean) {
        this.isFocusSearch = isFocusSearch
    }
}