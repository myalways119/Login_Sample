package uz.uchqun.login_sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.network.RemoteDataSource
import uz.uchqun.login_sample.core.network.UserApi
import uz.uchqun.login_sample.core.repository.BaseRepository
import uz.uchqun.login_sample.core.util.Utils.startNewActivity
import uz.uchqun.login_sample.ui.auth.AuthActivity
import uz.uchqun.login_sample.ui.base.viewModel.BaseViewModel
import uz.uchqun.login_sample.ui.base.viewModel.ViewModelFactory

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment()
{
    protected lateinit var userPreferences: UserPreferences
    protected lateinit var mBinding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        userPreferences = UserPreferences(requireContext())
        mBinding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch {
            userPreferences.authToken.first()
        }
        return mBinding.root
    }

    fun logout() =lifecycleScope.launch{
        val authToken = userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java,authToken)
        viewModel.logout(api)
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }
    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun getFragmentRepository(): R
}