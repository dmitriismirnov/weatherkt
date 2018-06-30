package smirnov.dmitrii.weatherkt.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.view_navigation_toolbar.view.*
import smirnov.dmitrii.weatherkt.R
import smirnov.dmitrii.weatherkt.extensions.tap

/**
 * @author Дмитрий
 * @version 30.06.2018.
 */
class NavigationToolbar(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var listener: OnToolbarClickListener? = null

    init {
        View.inflate(context, R.layout.view_navigation_toolbar, this)
        toolbar_icon_menu_btn.setOnClickListener { onMenuClick() }
        toolbar_icon_search_btn.setOnClickListener { onSearchClick() }
    }

    public fun setToolbarListener(listener: OnToolbarClickListener?) {
        this.listener = listener
    }

    fun onMenuClick() {
        listener?.onToolbarMenuClick()
    }

    fun onSearchClick() {
        listener?.onToolbarSearchClick()
        toolbar_icon_search_btn.tap()
    }


    public interface OnToolbarClickListener {
        fun onToolbarMenuClick()
        fun onToolbarSearchClick()
    }
}