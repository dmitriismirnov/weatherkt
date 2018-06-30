package smirnov.dmitrii.weatherkt.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    companion object {
        const val PREFS_CITY = "PREFS_CITY"
    }

    private var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(this)
    }
}