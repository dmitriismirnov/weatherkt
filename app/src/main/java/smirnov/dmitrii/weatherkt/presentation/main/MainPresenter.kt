package smirnov.dmitrii.weatherkt.presentation.main

import android.util.Log
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
class MainPresenter @Inject constructor(private val weatherInteractor: WeatherInteractor) : BasePresenter<MainView>() {
    override fun initialise() {
        getView()?.initialiseView()
    }

    fun requestCurrentLocationWeather(lat: Double, lon: Double) {
        weatherInteractor.getCurrentLocationWeather(latitude = lat, longitude = lon)
                .doOnSuccess { weather -> getView()?.showLocationWeather(weather) }
                .doOnSubscribe { getView()?.showProgress(false) }
                .doAfterTerminate { getView()?.showProgress(false) }
                .subscribe(
                        { getView()?.showLocationWeather(it) },
                        { getView()?.showError(it.localizedMessage) }
                )
                .connect()
    }

    fun request(city: String) {
        weatherInteractor.getCityWeather(city)
                .doOnSuccess { weather ->
                    getView()?.showLocationWeather(weather)
                    Log.e("doOnSuccess", weather.toString())
                    getView()?.showProgress(false)
                }
                .doOnError { error ->
                    getView()?.showError(error.localizedMessage)
                    Log.e("doOnError", error.localizedMessage, error.cause)
                    getView()?.showProgress(false)
                }
                .subscribe()
    }

}