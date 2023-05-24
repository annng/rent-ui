package com.crocodic.core.helper

import android.os.Build

/**
 * Created by Android Studio.
 * Project : CrocodicLibrary
 * User: ridwan akbar
 * Email: ridwansantos23@gmail.com
 * Telegram : @Ridwan_23
 * Date: 10/24/20
 * Time: 9:25 PM
 */
class DeviceHelper {

    companion object {

        fun getDeviceModel(): String {
            return "Android-" +
                    "${Build.VERSION.SDK_INT}-${Build.VERSION.CODENAME}-${Build.MANUFACTURER}-${Build.MODEL}-${Build.DEVICE}"
        }

    }

}