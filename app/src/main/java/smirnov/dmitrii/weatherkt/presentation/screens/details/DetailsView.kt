package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.support.v4.widget.SwipeRefreshLayout
import com.arellomobile.mvp.MvpView
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
interface DetailsView : MvpView {
    fun showWeather(weather: CurrentWeather)

}