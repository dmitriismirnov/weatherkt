package smirnov.dmitrii.weatherkt.presentation.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.presentation.base.BaseActivity
import smirnov.dmitrii.weatherkt.presentation.base.BaseFragment
import smirnov.dmitrii.weatherkt.presentation.base.BasePresenter
import smirnov.dmitrii.weatherkt.presentation.common.dialogs.SearchDialog
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsFragment
import smirnov.dmitrii.weatherkt.presentation.screens.map.WeatherMapFragment
import smirnov.dmitrii.weatherkt.presentation.widgets.NavigationToolbar
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, NavigationToolbar.OnToolbarClickListener, SearchDialog.OnClickListener {

    override fun getLayout() = R.layout.activity_main

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val SEARCH_DIALOG_TAG = "SEARCH_DIALOG_TAG"

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        startDetailedWeather()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(nav_drawer)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        nav_drawer.setNavigationItemSelectedListener { onNavigationItemSelected(it) }
        toolbar.setToolbarListener(this)
    }

    override fun onPause() {
        nav_drawer.setNavigationItemSelectedListener(null)
        toolbar.setToolbarListener(null)
        super.onPause()
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.details -> presenter.showCurrent()
            R.id.open_map -> presenter.showMap()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onToolbarMenuClick() {
        if (!drawer_layout.isDrawerOpen(nav_drawer)) {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onToolbarSearchClick() {
        presenter.showSearchCity()
    }

    override fun startDetailedWeather() {
        if (currentFragment !is DetailsFragment)
            switchFragment(DetailsFragment())
    }

    override fun startMap() {
        if (currentFragment !is WeatherMapFragment)
            switchFragment(WeatherMapFragment())
    }

    override fun showSearchCity() {
        SearchDialog
                .newInstants(
                        getString(R.string.search_dialog_title),
                        getString(R.string.search_dialog_hint),
                        getString(android.R.string.ok),
                        getString(android.R.string.cancel),
                        "search"
                ).show(supportFragmentManager, SEARCH_DIALOG_TAG)

    }

    override fun onSearchResult(tag: String, city: String) {
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .putString(BasePresenter.PREFS_CITY, city)
                .apply()
        switchFragment(DetailsFragment())
    }

    override fun toast(msg: String) = Toast
            .makeText(this, msg, Toast.LENGTH_SHORT).show()
}
