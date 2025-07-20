package com.example.chronos

import kotlin.collections.HashMap
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
     * Removes the timeline event(s) with the given uuid from the timeline
     * While there should not be multiple events with the same uuid,
     * this will remove ALL events with the given uuid
     */
    fun removeEventByUUID(eventUUID: Uuid,) {
        eventMap.entries.forEach { entry ->
            entry.value.removeIf { event -> event.eventUUID == eventUUID }
        }
        eventMap.entries.removeIf { entry -> entry.value.isEmpty() }
    }

    /**
     * Get the latest timestamp in this timeline (the largest timestamp value)
     * Return -1 if the timeline has no events
     */
    fun getLastestTimestamp(): Int {
        return -1
    }

    /**
     * Make the given event the first event in the timeline
     * (put at timestamp 0; all other events move up one)
     * ASSUMES EVENT IS NOT ALREADY IN THE TIMELINE
     */
    fun putEventFirst(event: TimelineEvent) {
        val updatedEventMap: HashMap<Int, ArrayList<TimelineEvent>> = hashMapOf()
        //update each event's internal timestamp by 1
        eventMap.entries.forEach { entry ->
            entry.value.forEach {
                event -> event.timestamp++
            }
        }
        //move all timeline entries up 1 timestamp key
        eventMap.entries.forEach { entry ->
            val newKey = entry.key+1
            updatedEventMap.put(newKey, entry.value)
        }
        //add event to first timestamp 0
        event.timestamp = 0
        updatedEventMap.put(event.timestamp, arrayListOf(event))
        //make updated map the event map
        eventMap = updatedEventMap
    }

    /**
     * given 2 timeline events, put 'newFirstEvent' before 'newSecondEvent'
     * which means - if newFirstEvent has the same timestamp as newSecondEvent
     * one or both timestamps will need to change
     *
     * todo: give function a better name
     */
    fun reorderEvents(newFirstEvent: TimelineEvent, newSecondEvent: TimelineEvent) {
        //if new first event is already first, then exit
        if (newFirstEvent.timestamp < newSecondEvent.timestamp) {
            return
        } else { //else actually do things
            //remove "new First Event" from current location
            removeEventByUUID(newFirstEvent.eventUUID)
            //update "new First Event's timestamp to be one before the "new second event"
            var newFirstEventTimestamp = newSecondEvent.timestamp - 1
            if (newFirstEventTimestamp < 0) {
                putEventFirst(newFirstEvent)
            } else {
                newFirstEvent.timestamp = newFirstEventTimestamp
                addEvent(newFirstEvent)
            }

        }
    }
}