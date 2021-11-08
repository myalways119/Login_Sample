package uz.uchqun.login_sample.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.uchqun.login_sample.core.model.LargeContentResponse
import uz.uchqun.login_sample.core.repository.LargeContentRepository
import uz.uchqun.login_sample.core.util.Resource
import uz.uchqun.login_sample.ui.base.viewModel.BaseViewModel

class LargeContentViewModel(private val repository: LargeContentRepository) : BaseViewModel(repository)
{
    private val _largeContentResponse: MutableLiveData<Resource<LargeContentResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LargeContentResponse>>
        get() = _largeContentResponse

    fun login(action: String, type: String) = viewModelScope.launch {
        _largeContentResponse.value = Resource.Loading
        _largeContentResponse.value = repository.SearchContent(action, type)
    }

    suspend fun savedAuthToken(token: String) {
        repository.saveAuthToken(token)
    }
}