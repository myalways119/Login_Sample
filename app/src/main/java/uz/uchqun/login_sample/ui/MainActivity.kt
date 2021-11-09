package uz.uchqun.login_sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import uz.uchqun.login_sample.R
import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.util.Utils.CheckPermissions
import uz.uchqun.login_sample.core.util.Utils.GetAndroidId
import uz.uchqun.login_sample.core.util.Utils.GetDevicePhoneNo
import uz.uchqun.login_sample.core.util.Utils.startNewActivity
import uz.uchqun.login_sample.ui.auth.AuthActivity
import uz.uchqun.login_sample.ui.home.HomeActivity

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun StartNextActivity()
    {
        var hasPermission:Boolean = false
        var hasPrevLoginInfo:Boolean = false
        val userPreferences = UserPreferences(this)

        if(CheckPermissions(this) == true)
        {
            hasPermission = true
        }

        userPreferences.authToken.asLiveData().observe(this, Observer
        {
            if(it != null)
            {
                if(userPreferences.androidId.toString().isNullOrEmpty() != true
                   && userPreferences.phoneNo.toString().isNullOrEmpty() != true)
                {
                    hasPrevLoginInfo = true;
                }
            }
        })

        var devicePhoneNo:String = GetDevicePhoneNo(this)
        var deviceAndroidId:String = GetAndroidId(this)

        if(devicePhoneNo.isNullOrEmpty() == true
           || deviceAndroidId.isNullOrEmpty() == true)
        {
            //Exit Program with warning message
        }

        val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
        startNewActivity(activity)
    }
}