package com.khvatid.binlistapp.ui.events

sealed class HistoryEvents: Event{
    object GetBinlistMap : HistoryEvents()
    object ClearBinlist: HistoryEvents()
}
