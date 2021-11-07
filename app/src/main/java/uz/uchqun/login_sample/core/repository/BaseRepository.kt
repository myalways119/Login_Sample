package uz.uchqun.login_sample.core.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import uz.uchqun.login_sample.core.network.UserApi
import uz.uchqun.login_sample.core.util.Resource

abstract class BaseRepository
{
    suspend fun <T> safeApiCall(apiCall:suspend ()->T):Resource<T>
    {
        return withContext(Dispatchers.IO){
            try
            {
                Resource.Success(apiCall.invoke())
            }catch (throwable:Throwable)
            {
                when(throwable){
                    is HttpException->{
                        Resource.Failure(true,throwable.code(), throwable.response()?.errorBody())
                    }
                    else ->{
                        Resource.Failure(true,null,null)
                    }
                }
            }
        }
    }

    suspend fun logout(api:UserApi) = safeApiCall{api.logout()}
}