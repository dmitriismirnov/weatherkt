package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
@InjectViewState
class DetailsPresenter @Inject constructor(private val weatherInteractor: WeatherInteractor) : BasePresenter<DetailsView>() {

    fun testLog(msg: String) {
        Log.d(javaClass.simpleName, msg)
    }

    fun requestWeather(city: String) {
        weatherInteractor
                .getCityWeather(city)
                .doOnSubscribe { viewState.showProgress(true) }
                .doAfterTerminate { viewState.showProgress(false) }
                .subscribe(
                        { viewState.showWeather(it)},
                        { viewState.showError(it)}
                )
                .connect()
    }
}