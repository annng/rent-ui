package com.rent.app.ui.home.review

import android.os.Bundle
import android.view.MenuItem
import com.rent.app.R
import com.rent.app.base.BaseActivity
import com.rent.app.data.home.Review
import com.rent.app.databinding.ActivityReviewBinding
import com.rent.app.util.ext.initItem

class ReviewActivity : BaseActivity<ActivityReviewBinding, ReviewViewModel>() {
    var reviews = ArrayList<Review>()
    lateinit var reviewAdapter : ReviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutRes(R.layout.activity_review)
        setToolbar(getString(R.string.toolbar_review))

        initView()
    }

    override fun initView() {
        super.initView()
        initAdapter()
    }

    private fun initAdapter(){
        reviews.addAll(Review.getReviews())
        reviews.addAll(Review.getReviews())
        reviews.addAll(Review.getReviews())

        reviewAdapter = ReviewAdapter(reviews){

        }

        binding.rvReview.initItem(this, reviewAdapter)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}