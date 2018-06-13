package smirnov.dmitrii.weatherkt.data.repository

import smirnov.dmitrii.weatherkt.app.system.SchedulersProvider
import smirnov.dmitrii.weatherkt.network.api.OpenWeatherMapApi
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
class WeatherRepository @Inject constructor(private val api: OpenWeatherMapApi,
                                            private val schedulers: SchedulersProvider) {
    fun getCurrentLocationWeather(cityName: String? = null,
                                  cityId: Long? = null,
                                  latitude: Double? = null,
                                  longitude: Double? = null,
                                  zipCode: String? = null
    ) = api.getCurrentLocationWeather(
            cityName,
            cityId,
            latitude,
            longitude,
            zipCode
    )
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun getDaysForecast(cityId: Long? = null,
                        latitude: Double? = null,
                        longitude: Double? = null,
                        zipCode: String? = null
    ) = api.getDaysForecast(
            cityId,
            latitude,
            longitude,
            zipCode
    )
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun getWeeksForecast(cityName: String? = null,
                         cityId: Long? = null,
                         count: Int? = null,
                         latitude: Double? = null,
                         longitude: Double? = null,
                         zipCode: String? = null
    ) = api.getWeeksForecast(
            cityName,
            cityId,
            count,
            latitude,
            longitude,
            zipCode
    )
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}