package com.example.chronos

import android.util.Log
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TimelineEvent(eventName: String, eventNotes: String = "",) {

    val eventUUID = Uuid.random();

    var eventName = eventName
    var eventNotes = eventNotes

    fun logUuid() {
        Log.d("TimelineEventClass","EventName: " + eventName+ " EventUUID: " + eventUUID)
    }
}