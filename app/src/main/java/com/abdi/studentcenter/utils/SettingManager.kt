package com.abdi.studentcenter.utils

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_FONT_SIZE = "font_size"
        private const val KEY_NOTIFICATION = "notification_enabled"
        private const val KEY_LANGUAGE = "language"
    }

    // Dark Mode
    var isDarkMode: Boolean
        get() = prefs.getBoolean(KEY_DARK_MODE, false)
        set(value) = prefs.edit().putBoolean(KEY_DARK_MODE, value).apply()

    // Font Size
    var fontSize: Int
        get() = prefs.getInt(KEY_FONT_SIZE, 14)
        set(value) = prefs.edit().putInt(KEY_FONT_SIZE, value).apply()

    // Notification
    var isNotificationEnabled: Boolean
        get() = prefs.getBoolean(KEY_NOTIFICATION, true)
        set(value) = prefs.edit().putBoolean(KEY_NOTIFICATION, value).apply()

    // Language
    var language: String
        get() = prefs.getString(KEY_LANGUAGE, "id") ?: "id"
        set(value) = prefs.edit().putString(KEY_LANGUAGE, value).apply()
}

// ===== Penggunaan di Activity =====
// val settings = SettingsManager(this)
//
// // Baca
// if (settings.isDarkMode) { /* aktifkan dark mode */ }
//
// // Tulis
// settings.isDarkMode = true
// settings.fontSize = 18