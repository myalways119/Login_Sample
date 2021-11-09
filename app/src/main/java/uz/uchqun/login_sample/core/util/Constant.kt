package uz.uchqun.login_sample.core.util

import android.Manifest

object Constant
{
    public val PermissionList = listOf (Manifest.permission.READ_PHONE_STATE)

    object ApiAction {
        const val SELECT_USER = "SELECT_USER"
        const val SELECT_LARGE_CONTENT = "SELECT_LARGE_CONTENT"
    }

    object LargeContentType {
        const val AGREE_CONTENT = "AGREE_CONTENT"
    }
}