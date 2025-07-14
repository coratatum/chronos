package com.example.chronos

import android.util.Log
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Timeline(var name: String, var events: ArrayList<TimelineEvent> = arrayListOf(),) {
    fun logEvents() {
        Log.d("Timeline", "TimelineName: " + name + " Timeline Events: " + events)
    }

    fun addEvent(newEvent: TimelineEvent,) {
        events.add(newEvent)
    }

    fun removeEventByUUID(eventUUID: Uuid,) {
        events.removeIf { timelineEvent -> timelineEvent.eventUUID == eventUUID }
    }
}