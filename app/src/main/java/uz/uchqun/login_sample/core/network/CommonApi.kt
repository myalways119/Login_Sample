package uz.uchqun.login_sample.core.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import uz.uchqun.login_sample.core.model.UserResponse

interface CommonApi
{
    @FormUrlEncoded
    @POST("CommonApi.php")
    suspend fun SelectUserInfo(
        @Field("action") action: String,
        @Field("phone_no") phone_no: String
    ): UserResponse
}