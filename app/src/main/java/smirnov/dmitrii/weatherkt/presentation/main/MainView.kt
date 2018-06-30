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

    fun toast(msg: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startDetailedWeather()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startMap()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSearchCity()
}