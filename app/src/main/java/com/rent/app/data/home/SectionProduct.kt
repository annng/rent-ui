package com.rent.app.data.home

import android.content.Context
import com.rent.app.R
import com.rent.app.data.product.Product

data class SectionProduct(
    var name : String = "",
    var info : String = "",
    var resId : Int = 0,
    var products : ArrayList<Product> = ArrayList()
){
    companion object{
        fun getSectionProducts(context: Context) : ArrayList<SectionProduct>{
            var sections = ArrayList<SectionProduct>()
            sections.add(SectionProduct(name = context.resources.getString(R.string.title_product_pilihan), info = context.resources.getString(R.string.subtitle_product_pilihan), resId = R.drawable.ic_produk_pilihan, products = Product.products()))
            sections.add(SectionProduct(name = context.resources.getString(R.string.title_product_promo), info = context.resources.getString(R.string.subtitle_product_promo), resId = R.drawable.ic_produk_promo, products = Product.products(false)))
            sections.add(SectionProduct(name = context.resources.getString(R.string.title_product_populer), info = context.resources.getString(R.string.subtitle_product_populer), resId = R.drawable.ic_produk_populer, products = Product.products()))

            return sections
        }
    }
}
