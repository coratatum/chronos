package com.example.chronos

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class Timeline(var name: String, var eventMap: HashMap<Int, ArrayList<TimelineEvent>> = hashMapOf(),) {

    //so when adding an event, get the timestamp, and add it to the list of that timestamp
    //in UI, when making a "new event" we would want to grab the latest timestamp,
    //and apply that+1 as the internal time for the new event. i think.

    /**
     * Adds the timeline event to the timeline
     */
    fun addEvent(newEvent: TimelineEvent,) {
        //i think this can be done more fancy with ?:
        if (eventMap.get(newEvent.timestamp) == null) {
            eventMap.put(newEvent.timestamp, arrayListOf(newEvent))
        } else {
            eventMap.get(newEvent.timestamp)?.add(newEvent)
        }
    }

    /**
     * Removes the timeline event with the given uuid from the timeline
     */
    fun removeEventByUUID(eventUUID: Uuid,) {
        //find the timeline event associated with the uuid
        //remove it from the list
        //if the resulting list is empty, delete the key/value pair completely
    }

    /**
     * Get the latest timestamp in this timeline (the largest timestamp value)
     * Return -1 if the timeline has no events
     */
    fun getLastestTimestamp(): Int {
        return -1;
    }

    /**
     * given 2 timeline events, put 'newFirstEvent' before 'newSecondEvent'
     * which means - if newFirstEvent has the same timestamp as newSecondEvent
     * one or both timestamps will need to change; may or may not be added to a previously existing list or create a new one
     * ...may or may not need to update the entire list
     * ...basically this is like, the whole thing right here isn't it
     */
    fun reorderEvents(newFirstEvent: TimelineEvent, newSecondEvent: TimelineEvent) {
        
    }
}