package io.realworld.android.extensions

import android.widget.TextView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
val appDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
fun TextView.showFormattedDate(timeStamp: String){
    val date = isoDateFormat.parse(timeStamp)
    text = appDateFormat.format(date)

}