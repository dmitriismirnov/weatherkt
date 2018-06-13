package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class Temp(
        @SerializedName("day") val day: Double?,
        @SerializedName("min") val min: Double?,
        @SerializedName("max") val max: Double?,
        @SerializedName("night") val night: Double?,
        @SerializedName("eve") val eve: Double?,
        @SerializedName("morn") val morn: Double?
)