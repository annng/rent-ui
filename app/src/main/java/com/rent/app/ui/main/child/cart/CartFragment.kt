package com.rent.app.ui.main.child.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.product.Cart
import com.rent.app.databinding.FragmentTroliBinding
import com.rent.app.ui.shipping.ShippingActivity
import com.rent.app.util.Constants
import com.rent.app.util.calendar.CalendarUtils
import com.rent.app.util.ext.*

class CartFragment : BaseFragment<FragmentTroliBinding, CartViewModel>(), View.OnClickListener {

    var carts: ArrayList<Cart> = Cart.getCarts()
    var selectedCarts = ArrayList<Cart>()
    lateinit var cartAdapter: CartAdapter
    var isCheckAll = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = bindView(inflater, R.layout.fragment_troli, container)
        initState(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    private fun observeData(){
        viewModel.obsSelectedCart.observe(viewLifecycleOwner, {
            selectedCarts.clear()
            selectedCarts.addAll(it)

            val i = Intent(requireContext(), ShippingActivity::class.java)
            i.putExtra(Constants.INTENT.KEY_ITEM, selectedCarts.getString())
            startActivityForResult(i, Constants.INTENT.REQ_INTENT)
        })
    }

    override fun initView() {
        super.initView()
        initBinding()
        initListener()
        initAdapter()
        updateCheckAll()
    }

    override fun initBinding() {
        super.initBinding()
        binding.fragment = this
        binding.isCheckedAll = isCheckAll
        binding.title = getString(R.string.nav_troli)

    }

    override fun initListener() {
        super.initListener()
        binding.ivDeleteAll.setOnClickListener(this)

    }

    override fun initAdapter() {
        super.initAdapter()
        cartAdapter = CartAdapter(carts, this) {}
        binding.rvCart.initItem(requireActivity(), cartAdapter)
    }

    fun updateCheckAll() {
        var checked = 0

        for (i in 0 until carts.size) {
            if (carts[i].isSelected) {
                checked++
            }
        }

        binding.tvCheckAll.text =
            resources.getString(R.string.label_pilih_semua, checked, carts.size)

        if (checked == carts.size) {
            isCheckAll = true
            binding.ivCheck.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_circle_checked
                )
            )
        } else {
            binding.ivCheck.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_circle_outline
                )
            )
            isCheckAll = false
        }

        binding.isCheckedAll = isCheckAll

        if (checked == 0) {
            binding.btnNext.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gray
                )
            )
            binding.btnNext.isClickable = false
            binding.ivDeleteAll.hide()
        } else {
            binding.btnNext.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.orange_dark
                )
            )
            binding.btnNext.isClickable = true
            binding.ivDeleteAll.show()
        }

        setSubtotal(carts)
    }

    fun toggleCheckedAll() {
        if (!isCheckAll) {
            for (i in 0 until carts.size) {
                carts[i].isSelected = true
            }
        } else {
            for (i in 0 until carts.size) {
                carts[i].isSelected = false
            }
        }

        updateCheckAll()
        cartAdapter.notifyDataSetChanged()
    }

    fun setSubtotal(carts: List<Cart>) {
        binding.tvSubTotalItem.text = resources.getString(R.string.label_subtotal_item, carts.size)

        var longSubtotal: Long = 0

        for (i in carts) {
            if (i.isSelected) {
                val sumDate = CalendarUtils.sumRangeDate(i.startDate, i.endDate) + 1
                longSubtotal += i.subTotal() * sumDate
            }
        }

        binding.tvSubTotalPrice.text = "Rp ${longSubtotal.priceFormat()}"

    }

    fun gotoShipping(){
        viewModel.getSelectedCart(carts)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.ivDeleteAll -> ToastHelper(requireContext()).showAToast("Delete All Selected Item")
        }
    }

}