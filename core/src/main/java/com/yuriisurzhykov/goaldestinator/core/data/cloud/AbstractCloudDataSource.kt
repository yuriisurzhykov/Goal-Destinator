package com.yuriisurzhykov.goaldestinator.core.data.cloud

import retrofit2.Response

abstract class AbstractCloudDataSource {

    protected suspend fun <T : CloudModel> handle(block: suspend () -> Response<T>): T {
        val response: Response<T>?
        try {
            response = block.invoke()
            return response.body()!!
        } catch (e: Exception) {
            throw e
        }
    }
}
