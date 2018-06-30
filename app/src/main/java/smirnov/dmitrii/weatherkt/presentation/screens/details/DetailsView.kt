package smirnov.dmitrii.weatherkt.presentation.screens.details

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
interface DetailsView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showWeather(weather: CurrentWeather)
    @StateStrategyType(SkipStrategy::class)
    fun showProgress(isRefreshing : Boolean)
    @StateStrategyType(SkipStrategy::class)
    fun showError(it: Throwable)
}