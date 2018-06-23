package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_detailed_weather.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.di.component.DaggerMainComponent
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.presentation.base.BaseFragment
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 16.06.2018.
 */
class DetailsFragment : BaseFragment(), DetailsView {
    override fun showError(it: Throwable) {
        Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override fun getLayout() = R.layout.fragment_detailed_weather

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var currentCity = "Saint Petersburg"

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.builder()
                .appComponent((activity!!.application as App).appComponent)
                .build()
                .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.testLog("onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        swipe_refresh.setOnRefreshListener { onRefresh() }
    }

    override fun onPause() {
        swipe_refresh.setOnRefreshListener(null)
        super.onPause()
    }

    fun onRefresh() {
        presenter.requestWeather(currentCity)
    }

    override fun showProgress(isRefreshing: Boolean) {
        swipe_refresh.isRefreshing = isRefreshing
    }

    override fun showWeather(weather: CurrentWeather) {
        presenter.testLog("show weather")
        testData.text = weather.toString()
    }

}