package uz.uchqun.login_sample.core.repository

import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.network.AuthApi

class AuthRepository(private val api: AuthApi, private val preferences: UserPreferences) : BaseRepository()
{
    suspend fun SelectUser(action: String, phoneNo: String) = safeApiCall {
        api.SelectUser(action, phoneNo)
    }

    suspend fun SelectContent(action: String, type: String) = safeApiCall {
        api.SelectContent(action, type)
    }

    suspend fun saveAuthToken(token: String) {preferences.savedAuthToken(token)}
}