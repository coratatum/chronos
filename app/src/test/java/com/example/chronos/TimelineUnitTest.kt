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
        assertNotNull(timeline.events)
    }

    @Test
    fun addTimelineEvent() {
        val timeline = Timeline("testTimeline")
        assertNotNull(timeline)
        assertEquals(0, timeline.events.size)

        val timelineEvent = TimelineEvent("testEvent", 0)
        val timelineEventUUID = timelineEvent.eventUUID
        timeline.addEvent(timelineEvent)
        assertEquals(1, timeline.events.size)
        assertEquals(timelineEventUUID, timeline.events[0].eventUUID)
    }

    @Test
    fun removeTimelineEventByUUID_eventExists(){
        val timelineEvent1 = TimelineEvent("testEvent", 0)
        val timeline = Timeline("testTimeline", arrayListOf(timelineEvent1))
        assertEquals(1, timeline.events.size)

        timeline.removeEventByUUID(timelineEvent1.eventUUID)
        assertEquals(0, timeline.events.size)
    }

    @Test
    fun removeTimelineEventByUUID_eventDoesNotExist(){
        val timelineEvent1 = TimelineEvent("testEvent", 0)
        val timeline = Timeline("testTimeline", arrayListOf(timelineEvent1))
        assertEquals(1, timeline.events.size)

        val randomUuid = Uuid.random()
        timeline.removeEventByUUID(randomUuid)
        assertEquals(1, timeline.events.size)
    }
}