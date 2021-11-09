package uz.uchqun.login_sample.core.repository

import android.content.Context
import uz.uchqun.login_sample.core.network.CommonApi
import uz.uchqun.login_sample.core.network.RetrofitClient

class CommonRepository(context: Context) : BaseRepository()
{

    val api: CommonApi = RetrofitClient.client!!.create(CommonApi::class.java)

}