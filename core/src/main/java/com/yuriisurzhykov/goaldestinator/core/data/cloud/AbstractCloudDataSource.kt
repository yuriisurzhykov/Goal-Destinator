package com.yuriisurzhykov.goaldestinator.core.data.cloud

import retrofit2.Response

abstract class AbstractCloudDataSource(
    private val service: String = ""
) {

    protected suspend fun <T : CloudModel> handle(block: suspend () -> Response<T>): T {
        val response: Response<T>?
        try {
            response = block.invoke()
            when (response.code()) {
                404 -> throw NoDataFoundException(service)
                in 500..599 -> throw ServiceNotAvailableException()
            }
            return response.body()!!
        } catch (e: Exception) {
            throw e
        }
    }
}
