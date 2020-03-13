package com.chetan.note.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Chetan on 12/03/20.
 */
val format = SimpleDateFormat("dd MMMM yyyy, h:mm a")

@BindingAdapter("app:last_update")
fun setLastUpdateDate(textView: TextView, date: Date) {
    val text = "Last updated on ${format.format(date)}"
    textView.text = text
}

@BindingAdapter("android:text")
fun setDate(textView: TextView, date: Date?) {
    date?.let {
        textView.text = format.format(it)
    }
}