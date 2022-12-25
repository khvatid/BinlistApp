package com.khvatid.binlistapp.ui.events

import android.content.Context

sealed class HomeEvents : Event {
    data class ChangeBIN(val value: String) : HomeEvents()
    object GetBinlist : HomeEvents()
    data class OpenPhoneActivity(val context: Context) : HomeEvents()
    data class OpenUrlActivity(val context: Context) : HomeEvents()
    data class OpenMapActivity(val context: Context) : HomeEvents()
}
