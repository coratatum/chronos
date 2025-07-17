package com.example.chronos

import org.junit.Test

import org.junit.Assert.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalUuidApi::class)
class TimelineUnitTest {
    @Test
    fun createTimeline() {
        val timeline = Timeline("name")
        assertNotNull(timeline)
        assertNotNull(timeline.eventMap)
    }

    @Test
    fun addTimelineEvent_timelineEmpty() {
        val timeline = Timeline("testTimeline")
        assertNotNull(timeline)
        assertEquals(0, timeline.eventMap.size)

        val timelineEvent = TimelineEvent("testEvent", 0)
        val timelineEventUUID = timelineEvent.eventUUID
        timeline.addEvent(timelineEvent)
        assertEquals(1, timeline.eventMap.size)
        assertEquals(1, timeline.eventMap.getValue(0).size)
        assertEquals(timelineEventUUID, timeline.eventMap.getValue(0)[0].eventUUID)
    }

    @Test
    fun addTimelineEvent_timestampExists() {
        val timelineEvent1 = TimelineEvent("testEvent", 0)
        val timelineMap = hashMapOf<Int, ArrayList<TimelineEvent>>()
        timelineMap.put(timelineEvent1.timestamp, arrayListOf(timelineEvent1))
        val timeline = Timeline("testTimeline", timelineMap)
        assertNotNull(timeline)
        assertEquals(1, timeline.eventMap.size)

        val timelineEvent = TimelineEvent("testEvent2", 0)
        timeline.addEvent(timelineEvent)
        assertEquals(1, timeline.eventMap.size)
        assertEquals(2, timeline.eventMap.getValue(0).size)
        //consider: maybe add more asserts on the events themselves??? not sure
    }

    /**
     * If the uuid is not found, delete nothing.
     */
    @Test
    fun removeTimelineEventByUUID_eventDoesNotExist(){
        val timelineEvent1 = TimelineEvent("testEvent", 0)
        val timelineMap = hashMapOf<Int, ArrayList<TimelineEvent>>()
        timelineMap.put(timelineEvent1.timestamp, arrayListOf(timelineEvent1))
        val timeline = Timeline("testTimeline", timelineMap)
        assertEquals(1, timeline.eventMap.size)

        val randomUuid = Uuid.random()
        timeline.removeEventByUUID(randomUuid)
        assertEquals(1, timeline.eventMap.size)
    }

    /**
     * If a timestamp has a single entry in the timeline, and it is deleted
     * remove the timestamp from the map completely
     */
    @Test
    fun removeTimelineEventByUUID_eventExistsAlone(){
        val timelineEvent1 = TimelineEvent("testEvent", 0)
        val timelineMap = hashMapOf<Int, ArrayList<TimelineEvent>>()
        timelineMap.put(timelineEvent1.timestamp, arrayListOf(timelineEvent1))
        val timeline = Timeline("testTimeline", timelineMap)
        assertEquals(1, timeline.eventMap.size)

        timeline.removeEventByUUID(timelineEvent1.eventUUID)
        assertEquals(0, timeline.eventMap.size)
    }

    /**
     * If a timestamp has multiple entries, and one is deleted, the rest should stay
     */
    @Test
    fun removeTimelineEventByUUID_eventExistsWithOthers(){
        val time = 0;
        val timelineEvent1 = TimelineEvent("testEvent", time)
        val timelineEvent2 = TimelineEvent("testEvent2", time)
        val timelineMap = hashMapOf<Int, ArrayList<TimelineEvent>>()
        timelineMap.put(time, arrayListOf(timelineEvent1, timelineEvent2))
        val timeline = Timeline("testTimeline", timelineMap)
        assertEquals(1, timeline.eventMap.size)
        assertEquals(2, timeline.eventMap.getValue(time).size)

        timeline.removeEventByUUID(timelineEvent1.eventUUID)
        assertEquals(1, timeline.eventMap.size)
        assertEquals(1, timeline.eventMap.getValue(time).size)
    }
}