package smirnov.dmitrii.weatherkt.entity.openweathermap

import com.google.gson.annotations.SerializedName

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
data class Sys(
        @SerializedName("type") val type: Int?,
        @SerializedName("id") val id: Long?,
        @SerializedName("message") val message: Double?,
        @SerializedName("country") val country: String?,
        @SerializedName("sunrise") val sunrise: Long?,
        @SerializedName("sunset") val sunset: Long?,
        @SerializedName("pod") val pod: String?
)