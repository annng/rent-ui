package com.rent.app.ui.order

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.order.OrderDetail
import com.rent.app.databinding.ActivityOrderDetailBinding
import com.rent.app.ui.order.child.OrderDetailProductFragment
import com.rent.app.ui.order.child.OrderDetailShippingFragment
import com.rent.app.ui.order.child.OrderDetailStatusFragment
import com.rent.app.util.ext.getString

class OrderDetailActivity : BaseActivity<ActivityOrderDetailBinding, OrderDetailViewModel>() {

    var orderDetail = OrderDetail.getOrderDetail()
    var arr_title: IntArray = intArrayOf(
        R.string.tab_title_status,
        R.string.tab_title_rincian,
        R.string.tab_title_pengiriman
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_order_detail)
        setToolbar(getString(R.string.nav_buku_sewa))

        initView()
    }

    override fun initView() {
        super.initView()

        setupViewPager(binding.vpDetailOrder, orderDetail)
    }

    private fun setupViewPager(viewPager: ViewPager, orderDetail: OrderDetail) {

        viewPager.offscreenPageLimit = arr_title.size
        val adapter = OrderViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(
            OrderDetailStatusFragment.newInstance(orderDetail.getString()),
            resources.getString(arr_title[0])
        )
        adapter.addFrag(
            OrderDetailProductFragment.newInstance(orderDetail.getString()),
            resources.getString(arr_title[1])
        )
        adapter.addFrag(
            OrderDetailShippingFragment.newInstance(orderDetail.getString()),
            resources.getString(arr_title[2])
        )
        viewPager.adapter = adapter

        binding.tabs.setViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}