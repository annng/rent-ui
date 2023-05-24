package com.rent.app.ui.main.child.home


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.crocodic.core.helper.ToastHelper
import com.rent.app.R
import com.rent.app.base.BaseFragment
import com.rent.app.data.home.Banner
import com.rent.app.data.home.Review
import com.rent.app.data.home.SectionProduct
import com.rent.app.databinding.FragmentBerandaBinding
import com.rent.app.ui.home.message.MessageActivity
import com.rent.app.ui.home.notification.NotificationActivity
import com.rent.app.ui.home.review.ReviewActivity
import com.rent.app.ui.main.MainActivity
import com.rent.app.ui.main.child.home.adapter.SectionProductAdapter
import com.rent.app.ui.product.list.ProductListActivity
import com.rent.app.ui.product.search.ProductSearchActivity
import com.rent.app.util.Constants
import com.rent.app.util.ext.initItem
import com.rent.app.widget.slider.CustomSliderImage
import com.rent.app.widget.slider.CustomSliderReview
import kotlinx.android.synthetic.main.toolbar_search.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<FragmentBerandaBinding, HomeViewModel>() {


    var isFocusSearch: Boolean = false
    lateinit var mainActivity: MainActivity

    //SLIDER
    var banners: ArrayList<Banner> = Banner.getBanners()
    lateinit var customSliderImage: CustomSliderImage

    //RATING
    var reviews: ArrayList<Review> = Review.getReviews()
    lateinit var customSliderReview: CustomSliderReview


    //Promo Product
    lateinit var filterProduct: ArrayList<SectionProduct>
    lateinit var sectionProductAdapter: SectionProductAdapter

    var strQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = bindView(inflater, R.layout.fragment_beranda, container)

        initState(view)
        mainActivity = activity as MainActivity


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBinding()
    }

    override fun initView() {
        super.initView()

        initSlider()

        initSliderReview()

        onSearchUI(isFocusSearch)

        initAdapterParentProduct()

        initListener()

        setBadgeMessage(4)
    }

    override fun initListener() {
        super.initListener()

        etSearch.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            isFocusSearch = hasFocus
            onSearchUI(hasFocus)
        }

        etSearch.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NAVIGATE_NEXT)
            ) {

                strQuery = etSearch.text.toString()

                if (strQuery.isEmpty()) {

                } else {
                    val intent = Intent(activity, ProductSearchActivity::class.java)
                    intent.putExtra(Constants.INTENT.KEY_QUERY, strQuery)
                    startActivityForResult(intent, Constants.INTENT.REQ_INTENT)
                }

                true
            }
            false
        }
    }

    override fun initBinding() {
        super.initBinding()
        binding.fragment = this
    }

    fun onSearchUI(isFocus: Boolean) {

        mainActivity.setIsFocusSearch(isFocusSearch)

        if (isFocus) {

            ivIconSearch.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_clear
                )
            )

            ivToolbarBack.setOnClickListener {
                etSearch.clearFocus()
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(etSearch.windowToken, 0)
            }

            ivIconSearch.setOnClickListener {
                etSearch.setText("")
            }

            ivToolbarIcon.visibility = View.INVISIBLE
            ivToolbarBack.visibility = View.VISIBLE

        } else {

            ivIconSearch.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_gray_search
                )
            )

            ivIconSearch.setOnClickListener {
                etSearch.requestFocus()
            }

            ivToolbarBack.visibility = View.INVISIBLE
            ivToolbarIcon.visibility = View.VISIBLE
        }

        binding.tvSeeReview.setOnClickListener {
            val i = Intent(activity, ReviewActivity::class.java)
            startActivityForResult(i, Constants.INTENT.REQ_INTENT)
        }
    }

    // Slider
    fun initSlider() {
        customSliderImage = CustomSliderImage(banners)
        binding.slPhoto.setSliderAdapter(customSliderImage)

        if (banners.size == 1) {
            binding.slPhoto.stopAutoCycle()
        }
    }

    // Review
    fun initSliderReview() {

        if (reviews.isEmpty())
            binding.llReview.visibility = View.GONE
        else
            binding.llReview.visibility = View.VISIBLE

        customSliderReview = CustomSliderReview(reviews)
        binding.slReview.setSliderAdapter(customSliderReview)

        if (reviews.size == 1) {
            binding.slReview.stopAutoCycle()
        }
    }

    //Promo Product
    fun initAdapterParentProduct() {
        filterProduct = SectionProduct.getSectionProducts(requireContext())
        sectionProductAdapter = SectionProductAdapter(filterProduct) {

        }
        binding.rvParentProduct.initItem(requireContext(), sectionProductAdapter)
    }

    fun gotoMessage() {
        var i = Intent(activity, MessageActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }

    fun gotoNotification() {
        var i = Intent(activity, NotificationActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }

    fun gotoProductList(){
        var i = Intent(activity, ProductListActivity::class.java)
        startActivityForResult(i, Constants.INTENT.REQ_INTENT)
    }


    private fun setBadgeMessage(count: Int) {
        if (count > 0) {
            binding.flPesanBadge.visibility = View.VISIBLE
            binding.flPesanBadge.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_badge_notification)
        } else {
            binding.flPesanBadge.visibility = View.VISIBLE
            binding.flPesanBadge.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_badge_grey)
        }
        binding.tvBadgeMessage.text = "$count"
    }

    fun submitWishlist() {
        ToastHelper(requireActivity()).showAToast(getString(R.string.toast_error_favorite_login))
    }
}// Required empty public constructor
