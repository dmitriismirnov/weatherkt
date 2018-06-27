package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.content.SharedPreferences
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.data.interaction.WeatherInteractor
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
@InjectViewState
class DetailsPresenter @Inject constructor(private val weatherInteractor: WeatherInteractor, private val preferences: SharedPreferences) : BasePresenter<DetailsView>() {

    fun testLog(msg: String) {
        Log.d(javaClass.simpleName, msg)
    }

    fun requestWeather() {
        weatherInteractor
                .getCityWeather(preferences.getString(PREFS_CITY, "Saint Petersburg"))
                .doOnSubscribe { viewState.showProgress(true) }
                .doAfterTerminate { viewState.showProgress(false) }
                .subscribe(
                        { onSuccess(it) },
                        { onFail(it) }
                )
                .connect()
    }

    private fun onSuccess(currentWeather: CurrentWeather?){
        viewState.showProgress(false)
        currentWeather?.let { viewState.showWeather(it) }
    }

    private fun onFail(t : Throwable){
        viewState.showProgress(false)
        viewState.showError(t)
    }
}