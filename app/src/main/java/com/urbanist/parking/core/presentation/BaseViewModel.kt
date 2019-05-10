package com.urbanist.parking.core.presentation

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val disposables = CompositeDisposable()

    open fun onUnbind() {
        disposables.dispose()
    }

    open fun onSaveInstanceState(state: Bundle) = Unit

    open fun onBind(state: Bundle? = null) = Unit

}