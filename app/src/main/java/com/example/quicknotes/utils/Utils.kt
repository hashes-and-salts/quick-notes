package com.example.quicknotes.utils

import androidx.compose.ui.graphics.Color
import com.example.quicknotes.ui.theme.dark_blue
import com.example.quicknotes.ui.theme.dark_brown
import com.example.quicknotes.ui.theme.dark_green
import com.example.quicknotes.ui.theme.dark_orange
import com.example.quicknotes.ui.theme.dark_purple
import com.example.quicknotes.ui.theme.dark_red
import com.example.quicknotes.ui.theme.dark_yellow
import com.example.quicknotes.ui.theme.light_blue
import com.example.quicknotes.ui.theme.light_brown
import com.example.quicknotes.ui.theme.light_green
import com.example.quicknotes.ui.theme.light_orange
import com.example.quicknotes.ui.theme.light_purple
import com.example.quicknotes.ui.theme.light_red
import com.example.quicknotes.ui.theme.light_yellow
import java.text.DateFormat
import java.util.Date
import java.util.Locale


fun formatTime(timeStamp: Long): String {
    val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
    return dateFormatter.format(Date(timeStamp))
}

val lightColors = mapOf(
    "blue" to light_blue,
    "green" to light_green,
    "yellow" to light_yellow,
    "orange" to light_orange,
    "red" to light_red,
    "purple" to light_purple,
    "brown" to light_brown,
    "none" to Color.Transparent
)

val darkColors = mapOf(
    "blue" to dark_blue,
    "green" to dark_green,
    "yellow" to dark_yellow,
    "orange" to dark_orange,
    "red" to dark_red,
    "purple" to dark_purple,
    "brown" to dark_brown,
    "none" to Color.Transparent
)

fun getNoteCardColor(colorString: String, isDarkModeOn: Boolean): Color? {
    return if(isDarkModeOn) {
        darkColors[colorString]
    } else {
        lightColors[colorString]
    }
}