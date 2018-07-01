package smirnov.dmitrii.weatherkt.presentation.screens.map

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.android.gms.maps.OnMapReadyCallback
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
interface WeatherMapView : MvpView, OnMapReadyCallback {
    @StateStrategyType(SkipStrategy::class)
    fun showProgress(isRefreshing: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showWeather(currentWeather: CurrentWeather)

    @StateStrategyType(SkipStrategy::class)
    fun showError(error: Throwable)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initialiseMap()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initialiseLocation()
}