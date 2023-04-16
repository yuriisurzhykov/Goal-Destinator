package com.yuriisurzhykov.goaldestinator.core.domain

interface Mapper<S : Any, R : Any> {
    fun map(source: S): R
}