package com.rent.app.ui.product.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.product.Product
import com.rent.app.databinding.ActivityProductDetailBinding
import com.rent.app.ui.main.child.home.adapter.ProductAdapter
import com.rent.app.util.ext.initItemHorizontal
import com.rent.app.widget.slider.CustomSliderProduct
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>() {

    var product = Product.product()
    var relatedProducts = Product.products()
    lateinit var coverageAreaAdapter: CoverageAreaAdapter
    lateinit var relatedProductAdapter : ProductAdapter

    lateinit var sliderProduct : CustomSliderProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_product_detail)
        setToolbar(product.productName, true)
        initView()

        observeData()
    }

    override fun initView() {
        super.initView()

        initSlider()
        initAdapter()
        initBind()
        initListener()

        selectDescription()
    }

    override fun initBind() {
        super.initBind()
        binding.product = product
        binding.context = this
    }

    override fun observeData() {
        super.observeData()

        viewModel.obsUpdateQty.observe(this, {
            etQty.setText(it.toString())
        })
    }

    private fun initSlider(){
        sliderProduct = CustomSliderProduct(product.productImages)
        binding.slPhoto.setSliderAdapter(sliderProduct)

        if (product.productImages.size == 1) {
            binding.slPhoto.stopAutoCycle()
        }
    }

    override fun initListener() {
        super.initListener()
        binding.ivIconFavorite.setOnClickListener(this)
        binding.ivIconShare.setOnClickListener(this)
        binding.btnSewa.setOnClickListener(this)
    }

    private fun initAdapter(){
        coverageAreaAdapter = CoverageAreaAdapter(product.coverageArea){

        }
        binding.rvArea.initItemHorizontal(this, coverageAreaAdapter)

        relatedProductAdapter = ProductAdapter(relatedProducts){
            ToastHelper(this).showAToast("Select ${relatedProducts[it].productName}")
        }
        binding.rvProduct.initItemHorizontal(this, relatedProductAdapter)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v){
            binding.ivIconFavorite -> ToastHelper(this).showAToast("Insert to Wishlist")
            binding.ivIconShare -> ToastHelper(this).showAToast("Share Product")
            binding.btnSewa -> ToastHelper(this).showAToast("Add to Cart")
        }
    }

    fun addQty(){
        try {
            val intQty = etQty.text.toString().toInt()
            viewModel.plusQty(intQty, product.stock)
        }catch (n : NumberFormatException){
            viewModel.plusQty(-1, product.stock)
        }
    }

    fun minQty(){
        try {
        val intQty = etQty.text.toString().toInt()
        viewModel.minQty(intQty)
            }catch (n : NumberFormatException){
            viewModel.minQty(1)
        }
    }

    fun initWebInfo(info: String) {
        val webSettings = wvInfo.settings
        wvInfo.loadData(info, "text/html", "utf-8")
        webSettings.javaScriptEnabled = true
    }

    fun selectDescription() {
        btnDeskripsi.setTextColor(ContextCompat.getColor(this, R.color.orange))
        btnSpesifikasi.setTextColor(ContextCompat.getColor(this, R.color.label_black))
        initWebInfo(product.description)
    }

    fun selectSpesification() {
        btnSpesifikasi.setTextColor(ContextCompat.getColor(this, R.color.orange))
        btnDeskripsi.setTextColor(ContextCompat.getColor(this, R.color.label_black))

        initWebInfo(product.spesification)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}