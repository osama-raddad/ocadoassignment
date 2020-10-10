package com.osama.data.network

import com.osama.repository.Result
import retrofit2.Response


internal class ResponseResultMapper {
    fun <T> mapResponse(response: Response<T>) = if (response.isSuccessful) {
        Result.Loaded(response.body())
    } else {
        Result.Error(response.raw().code(), response.message())
    }
}
