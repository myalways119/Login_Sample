package uz.uchqun.login_sample.core.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import uz.uchqun.login_sample.core.model.LargeContentResponse

interface LargeContentApi
{
    @FormUrlEncoded
    @POST("LargeContentApi.php")
    suspend fun SearchContent(
        @Field("action") action: String?,
        @Field("type") type: String,
    ): Call<LargeContentResponse>?
}