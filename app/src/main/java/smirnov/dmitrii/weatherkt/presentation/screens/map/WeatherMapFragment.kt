package smirnov.dmitrii.weatherkt.presentation.screens.map

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.app.App
import smirnov.dmitrii.weatherkt.entity.openweathermap.CurrentWeather
import smirnov.dmitrii.weatherkt.presentation.base.BaseFragment
import smirnov.dmitrii.weatherkt.presentation.common.dialogs.ProgressDialogFragment
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsFragment
import smirnov.dmitrii.weatherkt.presentation.widgets.PointDetailsWindow
import javax.inject.Inject

/**
 * @author Дмитрий
 * @version 23.06.2018.
 */
class WeatherMapFragment : BaseFragment(), WeatherMapView {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1059
        private const val REQUEST_CHECK_SETTINGS = 2
        private const val MAX_ZOOM = 10f
    }

    override fun getLayout() = R.layout.fragment_map

    @Inject
    @InjectPresenter
    lateinit var presenter: WeatherMapPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest

    private var locationUpdateState = false


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

    override fun onPause() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        super.onPause()
    }

    override fun initialiseMap(){
        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                lastLocation = p0.lastLocation
            }
        }

        createLocationRequest()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMapClickListener { point ->
            presenter.onPointClicked(point.latitude, point.longitude)
        }
        setUpMap()
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(context!!,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        googleMap.isMyLocationEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.setMaxZoomPreference(MAX_ZOOM)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, MAX_ZOOM))
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context!!,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(activity!!)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.d(this.tag, sendEx.message)
                }
            }
        }
    }

    override fun showProgress(isRefreshing: Boolean) {
        if (isRefreshing)
            ProgressDialogFragment.showDialog(activity!!.supportFragmentManager, "loading")
        else {
            ProgressDialogFragment.dismissIfExist(activity!!.supportFragmentManager)
        }
    }

    override fun showWeather(currentWeather: CurrentWeather) {
        googleMap.clear()
        with(currentWeather) {
            coordinate?.let {
                val latLng = com.google.android.gms.maps.model.LatLng(it.latitude!!, it.longitude!!)

                val markerOptions = com.google.android.gms.maps.model.MarkerOptions().position(latLng)
                        .title(name)
                        .snippet(base)


                val mapInfoWindow = PointDetailsWindow(
                        context!!,
                        currentWeather
                )

                googleMap.setInfoWindowAdapter(mapInfoWindow)

                googleMap.addMarker(markerOptions)
                        .showInfoWindow()

                googleMap.setOnInfoWindowClickListener {
                    currentWeather.name?.let { it1 -> showDetailsScreen(it1) }
                }
            }
        }
    }

    fun showDetailsScreen(city: String) {
        activity?.
                supportFragmentManager?.
                beginTransaction()?.
                replace(R.id.container, DetailsFragment())?.
                commitAllowingStateLoss()
    }

    override fun showError(error: Throwable) =
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_SHORT).show()


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }
    }
}