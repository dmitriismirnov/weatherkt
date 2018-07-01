package smirnov.dmitrii.weatherkt.data.interaction

import io.reactivex.Single
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.entity.openweathermap.DaysForecast

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
interface WeatherInteractor {
    fun getWeeksForecast(cityName: String,
                         cityId: Long,
                         count: Int,
                         latitude: Double,
                         longitude: Double,
                         zipCode: String
    ): Single<DaysForecast>

    fun getDaysForecast(cityId: Long?,
                        latitude: Double?,
                        longitude: Double?,
                        zipCode: String?
    ): Single<DaysForecast>

    fun getCityWeather(cityName: String): Single<CurrentWeather>

    fun getCurrentLocationWeather(lat: Double, lng: Double): Single<CurrentWeather>
}