package com.example.chronos

import android.util.Log
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TimelineEvent(var eventName: String, var timestamp: Int, var eventNotes: String = "",) {

    val eventUUID = Uuid.random()

    fun logUuid() {
        Log.d("TimelineEventClass","EventName: " + eventName + " EventUUID: " + eventUUID)
    }

    fun logNotes() {
        Log.d("TimelineEventClass","EventName: " + eventName + " EventNotes: " + eventNotes)
    }
}