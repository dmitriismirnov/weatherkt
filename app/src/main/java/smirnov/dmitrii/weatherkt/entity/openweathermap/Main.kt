package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class Main(
        @SerializedName("temp") val temp: Double?,
        @SerializedName("humidity") val humidity: Int?,
        @SerializedName("pressure") val pressure: Double?,
        @SerializedName("temp_min") val tempMin: Double?,
        @SerializedName("temp_max") val tempMax: Double?,
        @SerializedName("sea_level") val seaLevel: Double?,
        @SerializedName("grnd_level") val groundLevel: Double?,
        @SerializedName("temp_kf") val tempKf: Double?
)