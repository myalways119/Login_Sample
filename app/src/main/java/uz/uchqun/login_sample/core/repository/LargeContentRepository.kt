package uz.uchqun.login_sample.core.repository

import uz.uchqun.login_sample.core.data.UserPreferences
import uz.uchqun.login_sample.core.network.LargeContentApi

class LargeContentRepository(private val api: LargeContentApi, private val preferences: UserPreferences) : BaseRepository()
{
    suspend fun SearchContent(action: String, type: String) = safeApiCall {
        api.SearchContent(action, type)
    }

    suspend fun saveAuthToken(token: String) {preferences.savedAuthToken(token)}
}