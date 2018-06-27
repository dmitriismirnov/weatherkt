package smirnov.dmitrii.weatherkt.presentation.base

import android.content.Context
import android.content.SharedPreferences
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import smirnov.dmitrii.weatherkt.app.App

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    companion object {
        private const val PREFS_FILENAME = "PREFS_FILENAME"
        private const val PREFS_MODE = Context.MODE_PRIVATE
        const val PREFS_CITY = "PREFS_CITY"
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }
}