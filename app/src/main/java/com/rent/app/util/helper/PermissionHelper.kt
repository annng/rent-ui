package com.rent.app.util.helper

import android.Manifest
import android.app.Activity
import com.crocodic.widget.permission.PermissionActivity
import com.rent.app.R

class PermissionHelper(private val activity: Activity) {

    fun accessStorageCamera(granted: () -> Unit, denied: () -> Unit) {
        PermissionActivity(activity)
            .setPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .setContent(
                R.string.error_title_permission_storage,
                R.string.error_subtitle_permission_storage,
                R.string.error_title_permission_storage
            )
            .request({
                granted()
            }, {
                denied()
            })
    }
}