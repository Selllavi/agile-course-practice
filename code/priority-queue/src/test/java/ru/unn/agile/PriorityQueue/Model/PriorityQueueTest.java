package ru.unn.agile.PriorityQueue.model;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.*;

public class PriorityQueueTest {

    @Test
    public void canBeConstructed() {
        var pq = new PriorityQueue();
        assertNotEquals(pq, null);
    }

    @Test
    public void canCheckNullSize() {
        var pq = new PriorityQueue();
        boolean empty = pq.isEmpty();
        assertTrue(empty);
    }
}
