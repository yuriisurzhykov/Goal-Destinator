package com.yuriisurzhykov.goaldestinator.core.domain

interface ConnectivityCheck {
    fun check(): NetworkStatus
}