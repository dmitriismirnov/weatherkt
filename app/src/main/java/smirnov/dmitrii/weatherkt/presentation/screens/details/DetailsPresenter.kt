package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.content.SharedPreferences
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.entity.openweathermap.Coordinate
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.entity.openweathermap.DaysForecast
import smirnov.dmitrii.weatherkt.entity.openweathermap.Sys
import smirnov.dmitrii.weatherkt.extensions.toCelsiusString
import smirnov.dmitrii.weatherkt.extensions.toDirectionString
import smirnov.dmitrii.weatherkt.extensions.toSpeedString
import smirnov.dmitrii.weatherkt.extensions.toWindString
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsForecastItem.Companion.makeSunItem
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsForecastItem.Companion.makeWeatherItem
import java.util.*
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
@InjectViewState
class DetailsPresenter @Inject constructor(
        private val weatherInteractor: WeatherInteractor,
        private val preferences: SharedPreferences
) : BasePresenter<DetailsView>() {

    fun testLog(msg: String) {
        Log.d(javaClass.simpleName, msg)
    }

    fun requestWeather() {
        viewState.showProgress(true)
        weatherInteractor
                .getCityWeather(preferences.getString(PREFS_CITY, App.appContext.getString(R.string.saint_petersburg)))
                .subscribe(
                        { onSuccessWeather(it) },
                        { onFail(it) }
                )
                .connect()
    }

    private fun onSuccessWeather(currentWeather: CurrentWeather?) {
        viewState.showProgress(false)
        currentWeather?.let { viewState.showWeather(it) }
        currentWeather?.let {
            if (it.coordinate != null && it.sys != null)
                requestForecast(it.coordinate, it.sys)
        }
    }

    fun requestForecast(coordinate: Coordinate, sys: Sys) {
        weatherInteractor
                .getDaysForecast(
                        sys.id,
                        coordinate.latitude,
                        coordinate.longitude,
                        null
                )
                .subscribe(
                        { onSuccessForecast(it, sys) },
                        { onFail(it) }
                )
                .connect()
    }

    private fun onSuccessForecast(daysForecast: DaysForecast, sys: Sys) {
        viewState.showHoursForecast(generateForecastItems(daysForecast, sys))
    }

    private fun onFail(t: Throwable) {
        viewState.showProgress(false)
        viewState.showError(t)
    }

    private fun generateForecastItems(daysForecast: DaysForecast, sys: Sys): List<DetailsForecastItem> {
        var result: List<DetailsForecastItem> = ArrayList()

        daysForecast.forecastList?.forEach {
            result += makeWeatherItem(
                    it.dt,
                    it.main?.temp?.toCelsiusString(),
                    it.weatherList?.first()?.icon,
                    it.wind?.toWindString()
            )
        }
        result += makeSunItem(
                sys.sunrise,
                true
        )
        result += makeSunItem(
                sys.sunset,
                false
        )

        return result.sortedWith(compareBy(DetailsForecastItem::timestamp))
    }
}