package com.yuriisurzhykov.goaldestinator.core.domain

interface NetworkStatus {

    open class Normal : NetworkStatus
    object MobileNetwork : Normal()
    object WifiNetwork : Normal()
    open class NoConnectivity : NetworkStatus
    object TryingToConnect : NoConnectivity()
    object FailedConnect : NoConnectivity()
}