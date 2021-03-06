package smirnov.dmitrii.weatherkt.data.interaction

import smirnov.dmitrii.weatherkt.data.repository.WeatherRepository
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
class WeatherInteractorImpl @Inject constructor(private val weatherRepository: WeatherRepository) : WeatherInteractor {

    override fun getCurrentLocationWeather(lat: Double,
                                  lng: Double
    ) = weatherRepository.getCurrentLocationWeather(
            lat = lat,
            lng = lng)

    override fun getCityWeather(cityName: String
    ) = weatherRepository.getCurrentLocationWeather(
            cityName = cityName)

    override fun getDaysForecast(cityId: Long?,
                        latitude: Double?,
                        longitude: Double?,
                        zipCode: String?
    ) = weatherRepository.getDaysForecast(
            cityId = cityId,
            lat = latitude,
            lng = longitude,
            zip = zipCode)

    override fun getWeeksForecast(cityName: String,
                         cityId: Long,
                         count: Int,
                         latitude: Double,
                         longitude: Double,
                         zipCode: String
    ) = weatherRepository.getWeeksForecast(
            cityName = cityName,
            cityId = cityId,
            count = count,
            lat = latitude,
            lng = longitude,
            zip = zipCode)
}