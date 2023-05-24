package com.rent.app.ui.shipping

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.crocodic.core.extension.toList
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.order.Bank
import com.rent.app.data.product.Cart
import com.rent.app.data.shipping.City
import com.rent.app.data.shipping.District
import com.rent.app.data.shipping.SubDistrict
import com.rent.app.databinding.ActivityShippingBinding
import com.rent.app.util.Constants
import com.rent.app.util.calendar.CalendarUtils
import com.rent.app.util.ext.*

class ShippingActivity : BaseActivity<ActivityShippingBinding, ShippingViewModel>(),
    AdapterView.OnItemSelectedListener {


    var cities = City.getCities()
    var districts = District.getDistricts()
    var subDistrict = SubDistrict.getSubDistrict()

    var banks: ArrayList<Bank> = Bank.getBanks()
    lateinit var bankAdapter: BankAdapter

    var carts = ArrayList<Cart>()
    var voucherAmount : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_shipping)
        setToolbar(getString(R.string.toolbar_pengiriman))

        val strCarts = intent.getStringExtra(Constants.INTENT.KEY_ITEM)
        strCarts?.let {
            carts.addAll(it.toList())
        }

        initView()
    }

    override fun initView() {
        super.initView()
        initBind()
        initAdapter()
        initListener()
        setSubtotal(carts)
    }

    override fun initBind() {
        super.initBind()
        binding.context = this
    }

    override fun initListener() {
        super.initListener()
        binding.spCity.onItemSelectedListener = this
        binding.spDistrict.onItemSelectedListener = this
        binding.spSubDistrict.onItemSelectedListener = this

        bankAdapter = BankAdapter(banks) {
            bankAdapter.selectedPosition = it
            bankAdapter.notifyDataSetChanged()
            selectedBank(banks[it])
        }
        binding.rvBank.initItemHorizontal(this, bankAdapter)
    }

    private fun initAdapter() {
        binding.spCity.initWithHint(this, cities)
        binding.spDistrict.initWithHint(this, districts)
        binding.spSubDistrict.initWithHint(this, subDistrict)

    }

    private fun selectedBank(bank: Bank) {
        binding.llInfoBank.show()
        binding.ivImageBank.loadImage(bank.image)
        binding.tvBankName.text = bank.accountName
        binding.tvBankAccountNumber.text = bank.accountNumber

        binding.tvCopyRekening.setOnClickListener {
            val clipboard =
                getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = ClipData.newPlainText("banks", bank.accountNumber.replace(" ", ""))
            clipboard.setPrimaryClip(clip)

            ToastHelper(this).showAToast(getString(R.string.toast_copied_text))
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spCity -> {

            }
            binding.spDistrict -> {

            }
            binding.spSubDistrict -> {

            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun setSubtotal(carts: List<Cart>) {
        binding.tvSubTotalItem.text = getString(R.string.label_subtotal_item, carts.size)

        var l_Subtotal: Long = 0

        for (i in 0 until carts.size) {
            val sumDate = CalendarUtils.sumRangeDate(carts[i].startDate, carts[i].endDate) + 1

            l_Subtotal += carts[i].subTotal() * sumDate
        }

        binding.tvSubTotalPrice.text = "Rp ${(l_Subtotal - voucherAmount).priceFormat()}"
    }

    fun gotoPayment(){
        ToastHelper(this).showAToast("Submit and goto payment method")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}