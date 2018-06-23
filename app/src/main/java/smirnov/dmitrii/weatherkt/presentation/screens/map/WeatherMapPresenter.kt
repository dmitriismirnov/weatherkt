package smirnov.dmitrii.weatherkt.presentation.screens.map

import android.content.IntentSender
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
@InjectViewState
class WeatherMapPresenter @Inject constructor(private val weatherInteractor: WeatherInteractor): BasePresenter<WeatherMapView>() {

    fun onPointClicked(lat: Double, lon: Double) {
        requestLocationWeather(lat, lon)
    }

    private fun requestLocationWeather(lat: Double, lng: Double) {
        weatherInteractor.getCurrentLocationWeather(lat = lat, lng = lng)
                .doOnSubscribe { viewState.showProgress(true) }
                .doAfterTerminate { viewState.showProgress(false) }
                .subscribe(
                        { viewState.showWeather(it) },
                        { viewState.showError(it) }
                )
                .connect()
    }


}