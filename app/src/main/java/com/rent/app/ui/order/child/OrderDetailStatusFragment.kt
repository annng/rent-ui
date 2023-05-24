package com.rent.app.ui.order.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.order.OrderDetail
import com.rent.app.databinding.FragmentOrderDetailStatusBinding
import com.rent.app.ui.order.OrderDetailViewModel
import com.rent.app.util.ext.getObject
import com.rent.app.util.ext.initItem
import com.rent.app.util.ext.priceFormat
import com.rent.app.util.helper.dialog.DialogHelper
import kotlinx.android.synthetic.main.dialog_testimoni.view.*

class OrderDetailStatusFragment : BaseFragment<FragmentOrderDetailStatusBinding, OrderDetailViewModel>() {

    lateinit var orderDetail :OrderDetail
    lateinit var orderStatusAdapter : OrderDetailStatusAdapter
    companion object {

        val item = "item"

        fun newInstance(strItem: String): OrderDetailStatusFragment {
            val args = Bundle()
            args.putSerializable(item, strItem)
            val fragment = OrderDetailStatusFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = bindView(inflater, R.layout.fragment_order_detail_status, container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val strItem = requireArguments().getString(item)
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

        binding.tvBiayaPengiriman.text = orderDetail.deliveryCost.priceFormat()
        binding.tvBiayaSewa.text = orderDetail.subTotal.priceFormat()
        binding.tvDiskon.text = orderDetail.discountAmount.priceFormat()
        binding.tvGrandTotal.text = orderDetail.total.priceFormat()
    }

    override fun initAdapter() {
        super.initAdapter()
        orderStatusAdapter = OrderDetailStatusAdapter(orderDetail.statuses, orderDetail, this){

        }

        binding.rvStatus.initItem(requireActivity(), orderStatusAdapter)

    }


    fun dialogReview(){
        val dialog = DialogHelper(requireContext())
        dialog.dialogCustom(R.layout.dialog_testimoni){view ->

            var sumStart = 0
            view.rbRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                sumStart = ratingBar.progress
            }

            view.btnSend.setOnClickListener {
                if (sumStart == 0){
                    ToastHelper(requireContext()).showAToast("Please give rating to our service.")
                }else{
                    ToastHelper(requireContext()).showAToast("Sending Review...")
                }
            }
        }

        dialog.show()
    }

}