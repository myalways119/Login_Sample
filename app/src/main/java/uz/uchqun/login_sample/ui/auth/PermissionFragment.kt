package uz.uchqun.login_sample.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import uz.uchqun.login_sample.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PermissionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var navController :NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permission, container, false)
    }



}