package uz.uchqun.login_sample.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context)
{
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>

    init {dataStore = applicationContext.createDataStore(name = "my_data_store")}

    val authToken: kotlinx.coroutines.flow.Flow<String?>
        get() = dataStore.data.map { preferences -> preferences[KEY_AUTH]}

    suspend fun savedAuthToken(authToken: String)
    {
        dataStore.edit { preferences -> preferences[KEY_AUTH] = authToken}
    }

    suspend fun clear()
    {
        dataStore.edit { preferences -> preferences.clear()}
    }

    companion object
    {
        private val KEY_AUTH = stringPreferencesKey("key_auth")
    }
}