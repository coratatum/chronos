package com.example.chronos

import org.junit.Test

import org.junit.Assert.*
import kotlin.uuid.ExperimentalUuidApi

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalUuidApi::class)
class TimelineEventUnitTest {
    @Test
    fun createTimelineEvent() {
        val timelineEvent = TimelineEvent("testEvent", 0)
        assertNotNull(timelineEvent)
        assertEquals("testEvent", timelineEvent.eventName)
        assertEquals(0, timelineEvent.timestamp)
    }
}