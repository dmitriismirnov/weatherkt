package smirnov.dmitrii.weatherkt.presentation.screens.details

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hours_forecast.view.*
import kotlinx.android.synthetic.main.window_point_details.view.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.extensions.toDateString
import smirnov.dmitrii.weatherkt.extensions.toIconUrl
import smirnov.dmitrii.weatherkt.extensions.toTimeString
import smirnov.dmitrii.weatherkt.presentation.screens.details.DetailsForecastItem.Companion.TYPE_SUNRISE

/**
 * @author Дмитрий
 * @version 01.07.2018.
 */
class DetailsForecastAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items: List<DetailsForecastItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = inflater.inflate(R.layout.item_hours_forecast, parent, false);
        return when (viewType) {
            DetailsForecastItem.TYPE_WEATHER -> WeatherViewHolder(v)
            else -> SunViewHolder(v)
        }
    }

    override fun getItemViewType(position: Int) = items[position].type

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: DetailsForecastItem = items[position]
        if (holder is WeatherViewHolder) holder.bind(item)
        else (holder as? SunViewHolder)?.bind(item)
    }

    fun setItems(items: List<DetailsForecastItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class WeatherViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DetailsForecastItem) {
            Picasso.with(itemView.context).load(item.icon?.toIconUrl()).into(itemView.item_hours_icon)
            itemView.item_hours_date.text = item.timestamp?.toDateString()
            itemView.item_hours_time.text = item.timestamp?.toTimeString()
            itemView.item_hours_temperature.text = item.temperature
            itemView.item_hours_wind.text = item.wind
        }
    }

    class SunViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DetailsForecastItem) {
            itemView.item_hours_icon.setImageResource(
                    when (item.type) {
                        TYPE_SUNRISE -> R.drawable.v_ic_sunrise
                        else -> R.drawable.v_ic_sunset
                    }
            )
            itemView.item_hours_date.text = item.timestamp?.toDateString()
            itemView.item_hours_time.text = item.timestamp?.toTimeString()
//            itemView.item_hours_temperature.visibility = View.INVISIBLE
//            itemView.item_hours_wind_speed.visibility = View.INVISIBLE
//            itemView.item_hours_wind_deg.visibility = View.INVISIBLE
        }
    }
}