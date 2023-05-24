package com.rent.app.ui.order.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.order.OrderDetail
import com.rent.app.databinding.FragmentOrderDetailShippingBinding
import com.rent.app.ui.order.OrderDetailViewModel
import com.rent.app.util.ext.getObject
import com.rent.app.util.ext.initItem
import com.rent.app.util.ext.priceFormat

class OrderDetailShippingFragment : BaseFragment<FragmentOrderDetailShippingBinding, OrderDetailViewModel>() {

    lateinit var orderDetail : OrderDetail
    lateinit var orderShippingAdapter : OrderDetailShippingAdapter
    var locationShipping = ArrayList<String>()
    var labelShipping = ArrayList<String>()

    companion object {

        val item = "item"

        fun newInstance(strItem: String): OrderDetailShippingFragment {
            val args = Bundle()
            args.putSerializable(item, strItem)
            val fragment = OrderDetailShippingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = bindView(inflater, R.layout.fragment_order_detail_shipping, container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val strItem = requireArguments().getString(OrderDetailStatusFragment.item)
            strItem?.let { orderDetail = it.getObject() }
        }

        initView()
    }

    override fun initView() {
        super.initView()
        initAdapter()
        initBinding()
    }

    override fun initBinding() {
        super.initBinding()
        binding.tvBiayaPengiriman.text = orderDetail.deliveryCost.priceFormat()
    }

    override fun initAdapter() {
        super.initAdapter()
        addData()

        orderShippingAdapter = OrderDetailShippingAdapter(locationShipping, labelShipping){}
        binding.rvShipping.initItem(requireActivity(), orderShippingAdapter)

    }


    private fun addData(){
        locationShipping.add(orderDetail.delivery.name)
        locationShipping.add(orderDetail.delivery.phone)
        locationShipping.add(orderDetail.delivery.address)
        locationShipping.add(orderDetail.delivery.subDistrict)
        locationShipping.add(orderDetail.delivery.district)
        locationShipping.add(orderDetail.delivery.city)

        labelShipping.add(resources.getString(R.string.label_hint_nama))
        labelShipping.add(resources.getString(R.string.label_hint_no_ponsel))
        labelShipping.add(resources.getString(R.string.label_hint_alamat))
        labelShipping.add(resources.getString(R.string.label_hint_kelurahan))
        labelShipping.add(resources.getString(R.string.label_hint_kecamatan))
        labelShipping.add(resources.getString(R.string.label_hint_kota))
    }

}