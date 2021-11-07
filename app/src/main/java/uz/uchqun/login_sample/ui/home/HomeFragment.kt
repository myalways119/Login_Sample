package uz.uchqun.login_sample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import uz.uchqun.login_sample.core.model.User
import uz.uchqun.login_sample.core.network.UserApi
import uz.uchqun.login_sample.core.repository.UserRepository
import uz.uchqun.login_sample.core.util.Resource
import uz.uchqun.login_sample.core.util.visible
import uz.uchqun.login_sample.databinding.FragmentHomeBinding
import uz.uchqun.login_sample.ui.base.BaseFragment
import uz.uchqun.login_sample.ui.home.viewModel.HomeViewModel


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.homeProgressBar.visible(false)
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Success -> {
                    mBinding.homeProgressBar.visible(false)
                    updateUI(it.value.user)
                }

                is Resource.Loading -> {
                    mBinding.homeProgressBar.visible(true)
                }
            }
        })


        mBinding.homeLogOut.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(mBinding) {
            homeName.text = user.name
            homeEmail.text = user.email
        }
    }


    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)

        return UserRepository(api)
    }


}