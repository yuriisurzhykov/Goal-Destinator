package com.yuriisurzhykov.goaldestinator.quotes.data

import com.yuriisurzhykov.goaldestinator.core.data.retrofit.CreateService
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideRetrofitBuilder

interface QuoteCreateService : CreateService {

    class Base(
        provide: ProvideRetrofitBuilder
    ) : CreateService.Abstract("https://api.quotable.io/", provide)
}
