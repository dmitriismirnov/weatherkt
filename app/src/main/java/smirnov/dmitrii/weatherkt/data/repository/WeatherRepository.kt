package smirnov.dmitrii.weatherkt.data.repository

import io.reactivex.Single
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.entity.openweathermap.DaysForecast

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
interface WeatherRepository {

    fun getCurrentLocationWeather(
            cityName: String? = null,
            cityId: Long? = null,
            lat: Double? = null,
            lng: Double? = null,
            zip: String? = null
    ): Single<CurrentWeather>


    fun getDaysForecast(
            cityId: Long? = null,
            lat: Double? = null,
            lng: Double? = null,
            zip: String? = null
    ): Single<DaysForecast>

    fun getWeeksForecast(
            cityName: String? = null,
            cityId: Long? = null,
            count: Int? = null,
            lat: Double? = null,
            lng: Double? = null,
            zip: String? = null
    ): Single<DaysForecast>
}