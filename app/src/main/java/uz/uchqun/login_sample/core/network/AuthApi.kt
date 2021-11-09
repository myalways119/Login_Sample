package uz.uchqun.login_sample.core.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import uz.uchqun.login_sample.core.model.LargeContentResponse
import uz.uchqun.login_sample.core.model.UserResponse

interface AuthApi
{
    @FormUrlEncoded
    @POST("AuthApi.php")
    suspend fun SelectUser(
        @Field("action") action: String?,
        @Field("phone_no") phoneNo: String
    ): UserResponse

    @FormUrlEncoded
    @POST("AuthApi.php")
    suspend fun SelectContent(
        @Field("action") action: String?,
        @Field("type") type: String
    ): LargeContentResponse
}