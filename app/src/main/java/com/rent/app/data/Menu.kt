package com.rent.app.data

import com.rent.app.R

data class Menu(
    var resIcon : Int? = null,
    var resTitle : Int? = null
){
    companion object{
        fun getMenuAccount() : ArrayList<Menu>{
            var menus = ArrayList<Menu>()
            menus.add(Menu(R.drawable.ic_orange_outline_star_24dp, R.string.title_menu_item_favorit))
            menus.add(Menu(R.drawable.ic_edit_password, R.string.title_menu_edit_password))
            menus.add(Menu(R.drawable.ic_message, R.string.title_menu_perpesanan))
            menus.add(Menu(R.drawable.ic_faq, R.string.title_menu_faq))
            menus.add(Menu(R.drawable.ic_term, R.string.title_menu_syarat_ketentuan))
            menus.add(Menu(R.drawable.ic_about_us, R.string.title_menu_tentang_kami))
            menus.add(Menu(R.drawable.ic_feedback, R.string.title_menu_beri_feedback))
            menus.add(Menu(R.drawable.ic_privacy_policy, R.string.title_menu_privacy_policy))
            return menus
        }
    }
}
