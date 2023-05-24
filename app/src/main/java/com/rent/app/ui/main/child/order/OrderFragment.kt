package com.rent.app.ui.main.child.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.order.Order
import com.rent.app.databinding.FragmentSewaListBinding
import com.rent.app.ui.order.OrderDetailActivity
import com.rent.app.util.Constants
import com.rent.app.util.ext.initItem

class OrderFragment : BaseFragment<FragmentSewaListBinding, OrderViewModel>() {

    var orders : ArrayList<Order> = Order.getOrders()
    lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = bindView(inflater, R.layout.fragment_sewa_list, container)
        initState(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun initView() {
        super.initView()
        initAdapter()
        initBinding()
    }

    override fun initBinding() {
        super.initBinding()
        binding.title = getString(R.string.nav_buku_sewa)
    }

    override fun initAdapter() {
        super.initAdapter()
        orderAdapter = OrderAdapter(orders){
            val i = Intent(requireActivity(), OrderDetailActivity::class.java)
            startActivityForResult(i, Constants.INTENT.REQ_INTENT)
        }

        binding.rvOrder.initItem(requireContext(), orderAdapter)
    }

}