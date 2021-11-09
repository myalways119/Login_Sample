package uz.uchqun.login_sample.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.uchqun.login_sample.core.model.LoginResponse
import uz.uchqun.login_sample.core.repository.UserRepository
import uz.uchqun.login_sample.core.util.Resource
import uz.uchqun.login_sample.ui.base.viewModel.BaseViewModel

class HomeViewModel(private val repository: UserRepository) : BaseViewModel(repository)
{
    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user


    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}