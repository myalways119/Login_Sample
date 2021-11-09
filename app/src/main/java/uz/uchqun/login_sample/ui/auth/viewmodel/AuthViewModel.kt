package uz.uchqun.login_sample.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.uchqun.login_sample.core.model.LargeContentResponse
import uz.uchqun.login_sample.core.model.LoginResponse
import uz.uchqun.login_sample.core.model.UserResponse
import uz.uchqun.login_sample.core.repository.AuthRepository
import uz.uchqun.login_sample.core.util.Resource
import uz.uchqun.login_sample.ui.base.viewModel.BaseViewModel

class AuthViewModel(private val repository: AuthRepository) : BaseViewModel(repository)
{
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    private val _userResponse: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    private val _largeContentResponse: MutableLiveData<Resource<LargeContentResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun SearchUser(action:String, phoneNo:String) = viewModelScope.launch {
        _userResponse.value = Resource.Loading
        _userResponse.value = repository.SelectUser(action, phoneNo)
    }

    fun SearchLargeContent(action:String, type:String) = viewModelScope.launch {
        _largeContentResponse.value = Resource.Loading
        _largeContentResponse.value = repository.SelectContent(action, type)
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        //_loginResponse.value = repository.login(email, password)
    }

    suspend fun savedAuthToken(token: String)
    {
        repository.saveAuthToken(token)
    }
}