package com.yuriisurzhykov.goaldestinator.core.data.cloud

class NoDataFoundException(service: String) :
    RuntimeException("No data found for service: $service")
