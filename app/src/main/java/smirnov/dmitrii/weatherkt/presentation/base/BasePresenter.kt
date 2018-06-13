package smirnov.dmitrii.weatherkt.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
abstract class BasePresenter<out T> {
    private var view: T? = null

    private val compositeDisposable = CompositeDisposable()

    abstract fun initialise()

    fun getView(): T? = view

    @Suppress("UNCHECKED_CAST")
    fun attachView(view: Any?) {
        this.view = view as T?
    }

    fun detachView() {
        view = null
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }
}