package com.rent.app.ui.home.notification

import android.os.Bundle
import android.view.MenuItem
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.home.Notification
import com.rent.app.databinding.ActivityNotificationBinding
import com.rent.app.util.ext.initItem

class NotificationActivity : BaseActivity<ActivityNotificationBinding, NotificationViewModel>() {

    var notifications = Notification.getNotifications()
    lateinit var notificationAdapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_notification)
        setToolbar(getString(R.string.toolbar_notifikasi))

        initView()
    }

    override fun initView() {
        super.initView()

        initAdapter()
    }

    private fun initAdapter() {
        notificationAdapter = NotificationAdapter(notifications) {
            //todo goto detail notification. if isOrder -> go to detail order
        }

        binding.rvNotification.initItem(this, notificationAdapter)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}