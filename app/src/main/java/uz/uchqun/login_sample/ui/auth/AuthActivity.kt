package uz.uchqun.login_sample.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.uchqun.login_sample.R

class AuthActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    private fun SetIntializeNavi()
    {
        findNavController().navigate(R.id.flow_step_one_dest)
    }
}