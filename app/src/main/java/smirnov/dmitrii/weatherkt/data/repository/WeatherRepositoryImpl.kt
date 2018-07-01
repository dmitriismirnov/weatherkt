package smirnov.dmitrii.weatherkt.data.repository

import smirnov.dmitrii.weatherkt.app.system.SchedulersProvider
import smirnov.dmitrii.weatherkt.network.api.OpenWeatherMapApi
import javax.inject.Inject
import io.reactivex.SingleTransformer


/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
class WeatherRepositoryImpl @Inject constructor(
        private val api: OpenWeatherMapApi,
        private val schedulers: SchedulersProvider
) : WeatherRepository {

    override fun getCurrentLocationWeather(cityName: String?,
                                           cityId: Long?,
                                           lat: Double?,
                                           lng: Double?,
                                           zip: String?
    ) = api.getCurrentLocationWeather(
            cityName,
            cityId,
            lat,
            lng,
            zip
    )
            .compose(applySingleSchedulers())

    override fun getDaysForecast(cityId: Long?,
                                 lat: Double?,
                                 lng: Double?,
                                 zip: String?
    ) = api.getDaysForecast(
            cityId,
            lat,
            lng,
            zip
    )
            .compose(applySingleSchedulers())

    override fun getWeeksForecast(cityName: String?,
                                  cityId: Long?,
                                  count: Int?,
                                  lat: Double?,
                                  lng: Double?,
                                  zip: String?
    ) = api.getWeeksForecast(
            cityName,
            cityId,
            count,
            lat,
            lng,
            zip
    )
            .compose(applySingleSchedulers())

    fun <T> applySingleSchedulers(): SingleTransformer<T, T> = SingleTransformer { single ->
        single
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
    }

}