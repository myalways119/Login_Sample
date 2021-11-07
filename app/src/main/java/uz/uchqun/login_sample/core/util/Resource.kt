package uz.uchqun.login_sample.core.util

import okhttp3.ResponseBody


//Network api

sealed class Resource<out T>
{
    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ):Resource<Nothing>()

    object Loading:Resource<Nothing>()
}