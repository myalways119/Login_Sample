package uz.uchqun.login_sample.core.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val base_url = "https://freemon1.cafe24.com/myfirstdream/php/"
    private var retrofit: Retrofit? = null

    var gson = GsonBuilder()
        .setLenient()
        .create()

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }
}