package smirnov.dmitrii.weatherkt.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.di.component.DaggerMainComponent
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.presentation.base.BaseActivity
import smirnov.dmitrii.weatherkt.presentation.main.MainPresenter
import smirnov.dmitrii.weatherkt.presentation.main.MainView

class MainActivity : BaseActivity<MainPresenter>(), MainView, SwipeRefreshLayout.OnRefreshListener {

    override fun getLayout() = R.layout.activity_main

    override fun initInjector() {
        DaggerMainComponent.builder()
                .appComponent((application as App).appComponent)
                .build()
                .inject(this)
    }

    override fun onResume() {
        super.onResume()
        swipe_refresh.setOnRefreshListener(this)
    }

    override fun onPause() {
        swipe_refresh.setOnRefreshListener(null)
        super.onPause()
    }

    override fun initialiseView() {
    }

    override fun showProgress(show: Boolean) {
        swipe_refresh.isRefreshing = show
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLocationWeather(weather: CurrentWeather) {
        Log.d("debug", weather.toString())
        some_weather.text = weather.toString()
    }

    override fun onRefresh() {
        presenter.request("Saint Petersburg")
    }

}
