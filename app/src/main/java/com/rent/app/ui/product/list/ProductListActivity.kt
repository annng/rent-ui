package com.rent.app.ui.product.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.product.Product
import com.rent.app.data.shipping.City
import com.rent.app.databinding.ActivityProductBinding
import com.rent.app.databinding.ActivityProductSheetBinding
import com.rent.app.databinding.BottomSheetFilterBinding
import com.rent.app.databinding.BottomSheetSortirBinding
import com.rent.app.ui.product.detail.ProductDetailActivity
import com.rent.app.ui.product.search.ProductSearchActivity
import com.rent.app.util.Constants
import com.rent.app.util.ext.hide
import com.rent.app.util.ext.initItemGrid
import com.rent.app.util.ext.priceFormat
import com.rent.app.util.ext.show
import com.rent.app.util.helper.dialog.DialogHelper
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.dialog_slider.view.*

class ProductListActivity : BaseActivity<ActivityProductSheetBinding, ProductListViewModel>(),
    AdapterView.OnItemSelectedListener {
    var products = ArrayList<Product>()
    lateinit var productAdapter: ProductListAdapter

    lateinit var mainLayout: ActivityProductBinding
    lateinit var sheetFilter: BottomSheetFilterBinding
    lateinit var sheetSort: BottomSheetSortirBinding

    //Filter AND SORTIR
    lateinit var sheetBehavior: BottomSheetBehavior<*>
    var isOpenFilter = false
    var isOpenSortir = false
    var l_maxRange: Long = 0
    var l_minRange: Long = 0
    var cities: ArrayList<City> = City.getCities()

    val arrSortirLabel = arrayOf(
        "Newest",
        "Best Seller",
        "Lowest Price",
        "Highest Price"
    )

    lateinit var sortirAdapter: ArrayAdapter<*>
    lateinit var lokasiAdapter: ArrayAdapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_product_sheet)
        setToolbar(getString(R.string.toolbar_product))

        initView()
    }

    override fun initView() {
        super.initView()
        initState()
        initBind()
        initAdapter()
        initListener()

        setSampleData()
    }

    override fun initBind() {
        super.initBind()
        binding.context = this
        mainLayout = binding.mainLayout
        sheetFilter = binding.sheetFilter
        sheetSort = binding.sheetSort

        sheetFilter.tvRentangHarga.text = getString(R.string.label_rentang_harga_value,
            l_minRange.priceFormat(), l_maxRange.priceFormat())
    }

    override fun initListener() {
        super.initListener()
        mainLayout.btnBottom.llBtnFilter.setOnClickListener(this)
        mainLayout.btnBottom.llBtnSortir.setOnClickListener(this)
        sheetSort.btnSubmitSortir.setOnClickListener(this)
        sheetFilter.btnSubmitFilter.setOnClickListener(this)
        sheetFilter.tvRentangHarga.setOnClickListener(this)
    }

    private fun initAdapter() {
        productAdapter = ProductListAdapter(products) {
            val i = Intent(this, ProductDetailActivity::class.java)
            startActivity(i)
        }
        rvProduct.initItemGrid(this, productAdapter, 2)

        setSortirAdapter()
        setFilterLokasiAdapter()
    }

    private fun setSampleData() {
        products.clear()
        products.addAll(Product.products(false))
        products.addAll(Product.products())

        productAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v){
            mainLayout.btnBottom.llBtnFilter -> showFilter()
            mainLayout.btnBottom.llBtnSortir -> showSortir()
            sheetFilter.tvRentangHarga -> dialogRangePrice()
            sheetSort.btnSubmitSortir -> { sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN }
            sheetFilter.btnSubmitFilter -> {sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN}
        }
    }

    ///SORTIR AND FILTER
    fun setSortirAdapter() {
        sortirAdapter = ArrayAdapter(this, R.layout.item_spinner, arrSortirLabel)
        sortirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sheetSort.spSortir.adapter = sortirAdapter
        sheetSort.spSortir.onItemSelectedListener = this

    }

    private fun setFilterLokasiAdapter() {
        cities.add(0, City(value = "- All City -"))
        lokasiAdapter = ArrayAdapter(this, R.layout.item_spinner, cities)
        lokasiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sheetFilter.spLokasi.adapter = lokasiAdapter
        sheetFilter.spLokasi.onItemSelectedListener = this

    }

    fun showFilter() {
        sheetBehavior = BottomSheetBehavior.from<RelativeLayout>(sheetFilter.rlBottomSheetFilter)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> isOpenFilter = false
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        isOpenFilter = false
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN)

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                isOpenFilter = slideOffset >= 0

                val alpha = Integer.parseInt(Math.round(slideOffset).toString())

                if (slideOffset > 0) {
                    binding.vOverlay.show()
                } else {
                    binding.vOverlay.hide()
                }
            }
        })
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }


    fun showSortir() {
        sheetBehavior = BottomSheetBehavior.from<RelativeLayout>(sheetSort.rlBottomSheetSortir)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> isOpenFilter = false
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        isOpenFilter = false
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN)

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                isOpenFilter = slideOffset >= 0
                if (slideOffset > 0) {
                    binding.vOverlay.show()
                } else {
                    binding.vOverlay.hide()
                }
            }
        })
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun dialogRangePrice(minValueSet : Long = 100000, maxValueSet : Long = 10000000){
        val dialog = DialogHelper(this)
        dialog.dialogCustom(R.layout.dialog_slider){ view ->

            view.rangeSeekbar.setBarColor(R.color.colorPrimaryDark)
            view.rangeSeekbar.setBarHighlightColor(R.color.colorAccent)

            view.rangeSeekbar.setMaxStartValue(maxValueSet.toFloat() / 100000)
            view.rangeSeekbar.setMinStartValue(minValueSet.toFloat() / 100000)

            var minRange : Long = 0
            var maxRange : Long = 0


            view.rangeSeekbar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
                minRange = minValue as Long
                maxRange = maxValue as Long

                l_minRange = minRange
                l_maxRange = maxRange

                view.tvMinRange.text = minRange.priceFormat()
                view.tvMaxRange.text = maxRange.priceFormat()
            }


            view.positive.setOnClickListener {
                l_minRange = minRange
                l_maxRange = maxRange

                sheetFilter.tvRentangHarga.text = resources.getString(R.string.label_rentang_harga_value,
                    minRange.priceFormat(), maxRange.priceFormat())

                dialog.dismiss()
            }

            view.negative.setOnClickListener {
                l_minRange = 0
                l_maxRange = 0

                sheetFilter.tvRentangHarga.text = resources.getString(R.string.label_rentang_harga_value,
                    minRange.priceFormat(), maxRange.priceFormat())
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onBackPressed() {
        if(isOpenFilter || isOpenSortir){
            sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.search -> {
                val i = Intent(this, ProductSearchActivity::class.java)
                startActivityForResult(i, Constants.INTENT.REQ_INTENT)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }
}