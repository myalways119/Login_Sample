package uz.uchqun.login_sample.core.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.PhoneNumberUtils
import android.telephony.TelephonyManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import uz.uchqun.login_sample.ui.auth.LoginFragment
import uz.uchqun.login_sample.ui.base.BaseFragment
import java.util.*

object Utils
{
    @SuppressLint("MissingPermission", "HardwareIds")
    fun GetDevicePhoneNo(context: Context) : String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.line1Number
    }

    @SuppressLint("HardwareIds")
    fun GetAndroidId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun CheckPermissions(context: Context):Boolean
    {
        var hasPermission: Boolean = true
        var permission:String = String()

        while(permission in Constant.PermissionList)
        {
            if(ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            {
                hasPermission = false
            }
        }

        return hasPermission
    }

    fun <A : Activity> Activity.startNewActivity(activity: Class<A>)
    {
        Intent(this, activity).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    fun View.visible(isVisible: Boolean)
    {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun View.enable(enabled: Boolean)
    {
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }

    fun View.snackbar(message: String, action: (() -> Unit)? = null) {
        val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        action?.let {
            snackbar.setAction("Retry") {
                it()
            }
        }
        snackbar.show()
    }


    fun Fragment.handleApiError(
        failure: Resource.Failure,
        retry: (() -> Unit)? = null
    ) {
        when {
            failure.isNetworkError -> requireView().snackbar("Please check your internet", retry)
            failure.errorCode == 401 -> {
                if (this is LoginFragment) {
                    requireView().snackbar("You've entered incorrect email or password")
                } else {
                    (this as BaseFragment<*, *, *>).logout()
                }
            }
            else -> {
                val error = failure.errorBody?.string().toString()
                requireView().snackbar(error)
            }

        }
    }
}





