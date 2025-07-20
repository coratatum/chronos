package com.example.chronos

import android.util.Log
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class TimelineEvent(var eventName: String, var timestamp: Int, var eventNotes: String = "",) {

    //another question: does each event NEED to know its time, internally? should the timestamp only be tracked
    //at the timeline level? something to consider...
    val eventUUID = Uuid.random()

    fun logUuid() {
        Log.d("TimelineEventClass","EventName: " + eventName + " EventUUID: " + eventUUID)
    }

    fun logNotes() {
        Log.d("TimelineEventClass","EventName: " + eventName + " EventNotes: " + eventNotes)
    }
}