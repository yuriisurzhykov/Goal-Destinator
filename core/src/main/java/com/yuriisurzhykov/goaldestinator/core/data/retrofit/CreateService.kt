package com.yuriisurzhykov.goaldestinator.core.data.retrofit

interface CreateService {

    fun <T : Any> create(clazz: Class<T>): T

    abstract class Abstract(
        private val baseUrl: String,
        private val provider: ProvideRetrofitBuilder
    ) : CreateService {

        private val retrofit by lazy {
            provider.provideRetrofitBuilder()
                .baseUrl(baseUrl)
                .build()
        }

        override fun <T : Any> create(clazz: Class<T>): T = retrofit.create(clazz)
    }
}
