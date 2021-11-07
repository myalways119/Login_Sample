package uz.uchqun.login_sample.core.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import uz.uchqun.login_sample.core.model.LoginResponse

interface UserApi {

    @GET("user")
    suspend fun getUser():LoginResponse

    @POST("logout")
    suspend fun logout():ResponseBody
}