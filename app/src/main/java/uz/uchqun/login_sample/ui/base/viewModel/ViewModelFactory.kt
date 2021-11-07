package uz.uchqun.login_sample.ui.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.uchqun.login_sample.core.repository.AuthRepository
import uz.uchqun.login_sample.core.repository.BaseRepository
import uz.uchqun.login_sample.core.repository.UserRepository
import uz.uchqun.login_sample.ui.auth.viewmodel.AuthViewModel
import uz.uchqun.login_sample.ui.home.viewModel.HomeViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalAccessException("ViewModelClass Not Found")
        }
    }
}