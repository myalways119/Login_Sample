package uz.uchqun.login_sample.core.repository

import uz.uchqun.login_sample.core.data.UserPreferences

class LargeContentRepository(private val api: LargeContentApi, private val preferences: UserPreferences) : BaseRepository()
{
    suspend fun SearchContent(action: String, type: String) = safeApiCall {
        api.SearchContent(action, type)
    }

    suspend fun saveAuthToken(token: String) {preferences.savedAuthToken(token)}
}