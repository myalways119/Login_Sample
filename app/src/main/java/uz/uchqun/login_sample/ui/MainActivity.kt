package uz.uchqun.login_sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import uz.uchqun.login_sample.R
import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.util.CheckPermissions
import uz.uchqun.login_sample.core.util.Constant
import uz.uchqun.login_sample.core.util.startNewActivity
import uz.uchqun.login_sample.ui.auth.AuthActivity
import uz.uchqun.login_sample.ui.home.HomeActivity

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(CheckPermissions(this) == false)
        {
            //Go Permission Activity
        }

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer
        {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}