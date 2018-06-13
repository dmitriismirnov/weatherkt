package smirnov.dmitrii.weatherkt.data.interaction

import javax.inject.Inject
import smirnov.dmitrii.weatherkt.data.repository.WeatherRepository

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
class WeatherInteractor @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun getCurrentLocationWeather(latitude: Double,
                                  longitude: Double
    ) = weatherRepository.getCurrentLocationWeather(
            latitude = latitude,
            longitude = longitude)

    fun getCityWeather(cityName: String
    ) = weatherRepository.getCurrentLocationWeather(
            cityName = cityName)

    fun getDaysForecast(cityId: Long,
                        latitude: Double,
                        longitude: Double,
                        zipCode: String
    ) = weatherRepository.getDaysForecast(
            cityId = cityId,
            latitude = latitude,
            longitude = longitude,
            zipCode = zipCode)

    fun getWeeksForecast(cityName: String,
                         cityId: Long,
                         count: Int,
                         latitude: Double,
                         longitude: Double,
                         zipCode: String
    ) = weatherRepository.getWeeksForecast(
            cityName = cityName,
            cityId = cityId,
            count = count,
            latitude = latitude,
            longitude = longitude,
            zipCode = zipCode)
}