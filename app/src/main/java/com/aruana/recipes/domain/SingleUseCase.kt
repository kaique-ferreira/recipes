package com.aruana.recipes.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface SingleUseCase<T, in Params> {

    fun createSingleUseCase(params: Params): Single<T>

    fun invoke(onSuccess: ((t: T) -> Unit),
               onError: ((t: Throwable) -> Unit),
               onFinished: () -> Unit = {},
               params: Params) {
        dispose()
        disposable = createSingleUseCase(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(onFinished)
                .doOnError(onError)
                .subscribe { model -> handleSuccess(onSuccess, model) }
    }

    fun handleSuccess(onSuccess: (t: T) -> Unit, result: T) = onSuccess.invoke(result)

    var disposable: Disposable?

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}