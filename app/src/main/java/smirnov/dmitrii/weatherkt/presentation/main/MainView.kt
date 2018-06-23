package smirnov.dmitrii.weatherkt.presentation.main

import com.arellomobile.mvp.MvpView
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
interface MainView : MvpView {

    fun toast(msg: String)


    fun startDetailedWeather()
}