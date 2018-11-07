package io.indrian16.sembako.util

import android.support.v4.content.ContextCompat
import android.widget.Button
import io.indrian16.sembako.R

object CommonUtil {

    fun enableCreateButton(button: Button): Button {

        val context = button.context
        val colorPrimary = ContextCompat.getColor(context, R.color.colorPrimary)
        val colorWhite = ContextCompat.getColor(context, R.color.colorWhite)

        button.let {

            it.setBackgroundColor(colorPrimary)
            it.setTextColor(colorWhite)
            it.isEnabled = true
            return it
        }
    }

    fun disableCreateButton(button: Button): Button {

        val context = button.context
        val colorPrimary = ContextCompat.getColor(context, R.color.colorPrimary)
        val colorWhite = ContextCompat.getColor(context, R.color.colorWhite)

        button.let {

            it.setBackgroundColor(colorWhite)
            it.setTextColor(colorPrimary)
            it.isEnabled = false
            return it
        }
    }
}