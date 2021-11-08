package uz.uchqun.login_sample.ui.base.viewModel

import androidx.lifecycle.ViewModel
import uz.uchqun.login_sample.core.network.UserApi
import uz.uchqun.login_sample.core.repository.BaseRepository

abstract class BaseViewModel(private val repository: BaseRepository) :ViewModel()
{
    suspend fun logout(api:UserApi) = repository.logout(api)
}