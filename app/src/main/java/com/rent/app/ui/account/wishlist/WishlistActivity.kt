package com.rent.app.ui.account.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.product.Product
import com.rent.app.databinding.ActivityWishlistBinding
import com.rent.app.ui.main.child.home.adapter.ProductAdapter
import com.rent.app.ui.product.detail.ProductDetailActivity
import com.rent.app.util.Constants
import com.rent.app.util.ext.initItemGrid

class WishlistActivity : BaseActivity<ActivityWishlistBinding, WishlistViewModel>() {
    var products = Product.products()
    lateinit var productAdapter : ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_wishlist)
        setToolbar(getString(R.string.toolbar_item_favorit))

        initView()
    }

    override fun initView() {
        super.initView()

        initBind()
        initAdapter()
    }

    override fun initBind() {
        super.initBind()
    }

    private fun initAdapter(){
        productAdapter = ProductAdapter(products){
            val i = Intent(this, ProductDetailActivity::class.java)
            startActivityForResult(i, Constants.INTENT.REQ_INTENT)
        }

        binding.rvProduct.initItemGrid(this, productAdapter, 2)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}