package smirnov.dmitrii.weatherkt.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.di.component.AppComponent

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }
}