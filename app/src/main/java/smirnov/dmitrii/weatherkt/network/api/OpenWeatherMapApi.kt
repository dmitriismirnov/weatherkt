package smirnov.dmitrii.weatherkt.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.entity.openweathermap.DaysForecast

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
interface OpenWeatherMapApi {
    companion object {
        const val API_PATH = "data/2.5"
        const val APP_ID = "dd90f7680bbc95af1a2d3b62ed3e1eef"
    }

    @GET("${API_PATH}/weather?appid=${APP_ID}")
    fun getCurrentLocationWeather(@Query("q") cityName: String?,
                                  @Query("id") cityId: Long?,
                                  @Query("lat") latitude: Double?,
                                  @Query("lon") longitude: Double?,
                                  @Query("zip") zipCode: String?): Single<CurrentWeather>

    @GET("${API_PATH}/forecast?appid=${APP_ID}")
    fun getDaysForecast(@Query("id") cityId: Long?,
                        @Query("lat") latitude: Double?,
                        @Query("lon") longitude: Double?,
                        @Query("zip") zipCode: String?): Single<DaysForecast>

    @GET("${API_PATH}/forecast/daily?appid=${APP_ID}")
    fun getWeeksForecast(@Query("q") cityName: String?,
                         @Query("id") cityId: Long?,
                         @Query("cnt") count: Int?,
                         @Query("lat") latitude: Double?,
                         @Query("lon") longitude: Double?,
                         @Query("zip") zipCode: String?): Single<DaysForecast>
}