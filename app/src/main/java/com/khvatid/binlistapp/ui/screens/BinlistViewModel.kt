package com.khvatid.binlistapp.ui.screens

import androidx.lifecycle.ViewModel
import com.khvatid.binlistapp.ui.events.Event

abstract class BinlistViewModel<T: Event> : ViewModel() {

    abstract fun obtainEvent(event: T)

}