package com.rent.app.ui.order.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.order.OrderDetail
import com.rent.app.databinding.FragmentOrderDetailProductBinding
import com.rent.app.ui.order.OrderDetailViewModel
import com.rent.app.util.ext.getObject
import com.rent.app.util.ext.initItem
import com.rent.app.util.ext.priceFormat

class OrderDetailProductFragment : BaseFragment<FragmentOrderDetailProductBinding, OrderDetailViewModel>() {

    lateinit var orderDetail : OrderDetail
    lateinit var orderProductAdapter : OrderDetailProductAdapter

    companion object {

        val item = "item"

        fun newInstance(strItem: String): OrderDetailProductFragment {
            val args = Bundle()
            args.putSerializable(item, strItem)
            val fragment = OrderDetailProductFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindView(inflater, R.layout.fragment_order_detail_product, container)
        initState(view)
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


        initBinding()
        initAdapter()
    }

    override fun initBinding() {
        super.initBinding()
        binding.tvLabelSewa.text = subTotal().priceFormat()
    }

    override fun initAdapter() {
        super.initAdapter()
        orderProductAdapter = OrderDetailProductAdapter(orderDetail.products){

        }

        binding.rvProduct.initItem(requireActivity(), orderProductAdapter)
    }

    private fun subTotal() : Long{
        var subTotal : Long= 0
        for (i in orderDetail.products){
            subTotal += i.subTotal*i.qty
        }

        return subTotal
    }

}