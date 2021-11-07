package uz.uchqun.login_sample.core.repository

import uz.uchqun.login_sample.core.network.UserApi

class UserRepository(private val api: UserApi,) : BaseRepository()
{
    suspend fun getUser() = safeApiCall {api.getUser()}
}