package smirnov.dmitrii.weatherkt.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import smirnov.dmitrii.weatherkt.BuildConfig
import smirnov.dmitrii.weatherkt.network.api.OpenWeatherMapApi
import javax.inject.Singleton

/**
 * @author Дмитрий
 * @version 03.06.2018.
 */
@Module
class NetworkModule {

    private val weatherApi: OpenWeatherMapApi

    init {
        val okHttpBuilder = OkHttpClient.Builder()

        okHttpBuilder.addInterceptor { chain ->
            println(chain.request())
            chain.proceed(chain.request())
        }

        val okHttpClient = okHttpBuilder.build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(BuildConfig.SERVICE_ENDPOINT)
                .client(okHttpClient)
                .build()

        weatherApi = retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideWeatherService(): OpenWeatherMapApi = weatherApi
}