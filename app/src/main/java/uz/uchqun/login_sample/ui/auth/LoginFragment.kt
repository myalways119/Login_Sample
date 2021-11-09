package uz.uchqun.login_sample.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import uz.uchqun.login_sample.R
import uz.uchqun.login_sample.core.network.AuthApi
import uz.uchqun.login_sample.core.repository.AuthRepository
import uz.uchqun.login_sample.core.util.*
import uz.uchqun.login_sample.databinding.FragmentLoginBinding
import uz.uchqun.login_sample.ui.auth.viewmodel.AuthViewModel
import uz.uchqun.login_sample.ui.base.BaseFragment
import uz.uchqun.login_sample.ui.home.HomeActivity
import android.telephony.TelephonyManager
import uz.uchqun.login_sample.core.util.Utils.enable
import uz.uchqun.login_sample.core.util.Utils.handleApiError
import uz.uchqun.login_sample.core.util.Utils.startNewActivity
import uz.uchqun.login_sample.core.util.Utils.visible

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>()
{
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        mBinding.loginProgressBar.visible(false)
        mBinding.loginBtn.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            mBinding.loginProgressBar.visible(it is Resource.Loading)
            when(it){
                is Resource.Success->{
                    lifecycleScope.launch {
                        viewModel.savedAuthToken(it.value.user.access_token)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure->handleApiError(it){
                    login()
                }
            }
        })

        mBinding.lgEditEmail.addTextChangedListener {
            val email = mBinding.lgEditEmail.text.toString().trim()
            mBinding.loginBtn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        mBinding.loginBtn.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = mBinding.lgEditEmail.text.toString().trim()
        val password = mBinding.lgEditPassword.text.toString().trim()
        viewModel.login(email,password)
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    fun GetDevicePhoneNo(context: Context) : String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.line1Number
    }

    @SuppressLint("HardwareIds")
    fun GetAndroidID(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    private fun DoSearchLargeContent(){
        //val email = mBinding.lgEditEmail.text.toString().trim()
        //val password = mBinding.lgEditPassword.text.toString().trim()
        val type:String = Constant.LargeContentType.AGREE_CONTENT
        viewModel.SearchLargeContent( Constant.ApiAction.SELECT_LARGE_CONTENT, type )
    }

    private fun DoSearchUser(){
        val phoneNo:String = GetDevicePhoneNo(requireContext())
        viewModel.SearchUser( Constant.ApiAction.SELECT_USER, phoneNo)
    }

    override fun getViewModel(): Class<AuthViewModel>  = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding  = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}