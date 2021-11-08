package uz.uchqun.login_sample.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context)
{
    //private val applicationContext = context.applicationContext
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>

    init {dataStore = applicationContext.createDataStore(name = "my_data_store")}

    val authToken: kotlinx.coroutines.flow.Flow<String?>
        get() = dataStore.data.map { preferences -> preferences[KEY_ANDROID_ID]}

    val phoneNo: kotlinx.coroutines.flow.Flow<String?>
        get() = dataStore.data.map { preferences -> preferences[KEY_PHONE_NO]}

    val androidId: kotlinx.coroutines.flow.Flow<String?>
        get() = dataStore.data.map { preferences -> preferences[KEY_ANDROID_ID]}

    suspend fun savedAuthToken(authToken: String)
    {
        dataStore.edit { preferences -> preferences[KEY_ANDROID_ID] = authToken}
    }

    suspend fun savedPhoneInfo(phoneNo: String, androidId: String)
    {
        dataStore.edit { preferences -> preferences[KEY_PHONE_NO] = phoneNo}
        dataStore.edit { preferences -> preferences[KEY_ANDROID_ID] = androidId}
    }

    suspend fun clear()
    {
        dataStore.edit { preferences -> preferences.clear()}
    }

    companion object
    {
        private val KEY_AUTH = stringPreferencesKey("KEY_AUTH")
        private val KEY_PHONE_NO = stringPreferencesKey("PHONE_NO")
        private val KEY_ANDROID_ID = stringPreferencesKey("ANDROID_ID")
    }
}