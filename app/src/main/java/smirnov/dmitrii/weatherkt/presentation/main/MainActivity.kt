package smirnov.dmitrii.weatherkt.presentation.main

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.gms.maps.MapFragment
import kotlinx.android.synthetic.main.activity_main.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.di.component.DaggerMainComponent
import smirnov.dmitrii.weatherkt.presentation.base.BaseActivity
import smirnov.dmitrii.weatherkt.presentation.base.BaseFragment
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsFragment
import smirnov.dmitrii.weatherkt.presentation.screens.map.WeatherMapFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    override fun getLayout() = R.layout.activity_main

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent.builder()
                .appComponent((application as App).appComponent)
                .build()
                .inject(this)

        super.onCreate(savedInstanceState)
        startDetailedWeather()

    }


    override fun onResume() {
        super.onResume()
        nav_drawer.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
    }

    override fun onPause() {
        nav_drawer.setNavigationItemSelectedListener(null)
        super.onPause()
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.current -> presenter.showCurrent()
            R.id.search_city -> presenter.showSearchCity()
            R.id.open_map -> presenter.showMap()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun startDetailedWeather() {
        switchFragment(DetailsFragment())
    }

    override fun startMap() {
        switchFragment(WeatherMapFragment())
    }

    override fun showSearchCity() {
        toast("showSearchCity")
    }

    override fun toast(msg: String) = Toast
            .makeText(this, msg, Toast.LENGTH_SHORT).show()
}
