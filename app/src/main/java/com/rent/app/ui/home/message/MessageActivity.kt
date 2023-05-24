package com.rent.app.ui.home.message

import android.os.Bundle
import android.view.MenuItem
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.home.Message
import com.rent.app.databinding.ActivityMessageBinding
import com.rent.app.util.ext.initItem

class MessageActivity : BaseActivity<ActivityMessageBinding, MessageViewModel>() {

    var messages = Message.getMessage()
    lateinit var messageAdapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_message)
        setToolbar(getString(R.string.toolbar_perpesanan))

        initView()
    }

    override fun initView() {
        super.initView()
        initBind()
        initAdapter()
    }

    override fun initBind() {
        super.initBind()
        binding.context = this
    }

    private fun initAdapter(){
        messageAdapter = MessageAdapter(messages){

        }

        binding.rvMessage.initItem(this, messageAdapter)
    }

    fun submitMessage(){
        if (isFill(binding.etMessage)) {
            val strMessage = binding.etMessage.text.toString()
            //todo send to API
            binding.etMessage.clearFocus()
            binding.etMessage.setText("")
            ToastHelper(this).showAToast("Sending Message...")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}