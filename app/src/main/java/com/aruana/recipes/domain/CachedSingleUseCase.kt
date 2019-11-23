package com.aruana.recipes.domain

interface CachedSingleUseCase<T, in Params> : SingleUseCase<T, Params> {

    fun shouldCacheResult() = false

    fun addToCache(model: T)

    override fun handleSuccess(onSuccess: (t: T) -> Unit, result: T) {
        if (shouldCacheResult()) {
            addToCache(result)
        }

        onSuccess.invoke(result)
    }
}