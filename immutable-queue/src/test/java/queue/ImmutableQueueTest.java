package queue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ImmutableQueueTest {

    @Test
    public void run() {
        final Queue<Integer> queue = new ImmutableQueue<>(0);
        assertFalse(queue.isEmpty());
        assertTrue(queue.head().equals(0));

        final Queue<Integer> enQueued = queue.enQueue(1);
        assertTrue(enQueued.head().equals(0));

        final Queue<Integer> enQueued2nd = enQueued.enQueue(2);
        assertTrue(enQueued.head().equals(0));

        final Queue<Integer> deQueued = enQueued2nd.deQueue();
        assertTrue(deQueued.head().equals(1));

        final Queue<Integer> deQueued2nd = deQueued.deQueue();
        assertTrue(deQueued2nd.head().equals(2));

        final Queue<Integer> removed = deQueued2nd.deQueue();
        assertTrue(removed.isEmpty());
    }
}
