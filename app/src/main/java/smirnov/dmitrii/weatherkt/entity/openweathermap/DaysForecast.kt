package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class DaysForecast(
        @SerializedName("city") val city: City?,
        @SerializedName("coord") val coordinate: Coordinate?,
        @SerializedName("country") val country: String?,
        @SerializedName("cod") val cod: Int?,
        @SerializedName("message") val message: Double?,
        @SerializedName("cnt") val cnt: Int?,
        @SerializedName("list") val forecastList: List<Forecast>?
)