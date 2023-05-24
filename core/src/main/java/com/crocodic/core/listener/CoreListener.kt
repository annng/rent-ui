package com.crocodic.core.listener

import android.widget.ImageView
import android.widget.TextView

interface CoreListener {

    /**
     * Semua event listener di inisialisasikan pada method initListener()
     */
    fun initListener()

    /**
     * Semua view di inisialisasikan pada method initView()
     */
    fun initView()

    /**
     * Semua variable data binding
     */
    fun initBind()

    /**
     * State untuk menampilkan status loading, empty, error
     *
     */
    fun initStateView()

    /**
     * Default untuk mengeset title toolbar
     *
     * @param title menu title
     */
    fun setToolbarTitle(title: String?)

    /**
     * Mengembalikan image view logout
     *
     * @return image view nullable
     */
    fun getViewLogout(): ImageView?

    /**
     * Mengembalikan image view action back
     *
     * @return image view nullable
     */
    fun getViewBack(): ImageView?

    /**
     * Mengembalikan image view action back
     *
     * @return image view nullable
     */
    fun getViewHome(): ImageView?

    /**
     * Mengembalikan toolbar text view
     *
     * @return Textview toolbar nullable
     */
    fun getViewToolbarTitle(): TextView?


    /**
     * @return true if child activity should use mData binding instead of [.setContentView]
     */
    @Deprecated("tidak digunakana lagi, gunakan core sesuai kebutuhan")
    fun shouldUseDataBinding(): Boolean {
        return true
    }

    /**
     * Dapat dipanggil ketika terdapat data intent yang dikirim dari activity sebelumnya
     */
    fun readFromIntent()

    /**
     * Dapat dipanggil pada saat ingin melakukan observer pada variable LiveData
     */
    fun observeData()

    /**
     * Dapat dipanggil pertama kali pada saat ingin menampilkan state view.
     */
    fun onFirstLoad()

    /**
     * Dapat dipanggil pada saat akan melakukan refreh  view.
     */
    fun onRefresh()


    /**
     * Dapat dipanggil pada saat selesai load view.
     * @see onFirstLoad
     * @see onRefresh
     */
    fun onLoadFinish()


    /**
     * Dapat dipanggil ingin menampilkan content.
     */
    fun showContentView()


}