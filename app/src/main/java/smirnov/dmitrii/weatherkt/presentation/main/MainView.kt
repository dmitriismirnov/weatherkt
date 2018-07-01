package smirnov.dmitrii.weatherkt.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
interface MainView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun toast(msg: String)

    @StateStrategyType(SkipStrategy::class)
    fun startDetailedWeather()

    @StateStrategyType(SkipStrategy::class)
    fun startMap()

    @StateStrategyType(SkipStrategy::class)
    fun showSearchCity()
}