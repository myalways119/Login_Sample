package uz.uchqun.login_sample.core.repository

import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.network.AuthApi

class AuthRepository(private val api: AuthApi, private val preferences: UserPreferences) : BaseRepository()
{
    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String) {preferences.savedAuthToken(token)}
}