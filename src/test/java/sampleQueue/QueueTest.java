package sampleQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class QueueTest {

	/**
	 * Tests for Queue.
	 */

	private static final String SOME_ITEM = "some-content";
	private Queue<String> q;

	@Test
	@Disabled
	@DisplayName("is instantiated with new Queue()")
	void isInstantiatedWithNew() {
		new Queue<>();
	}

	@BeforeEach
	void init() {
		this.q = new Queue<String>();
	}

	@Test
	@DisplayName("Verify Queue isEmpty when queue is initialized")
	void isEmptyShouldGiveTrueOnQueueInit() {
		assertTrue(q.isEmpty());
	}
	@Test
	@DisplayName("Peek should throw an Exception if called on empty queue")
	void peekThrowsExceptionOnEmpty(){
		assertThrows(NoSuchElementException.class, () -> this.q.peek());
	}

	//Example of Wrong Test! 
	@Test
	@Disabled
	@DisplayName("Verify Queue isEmpty returns false when queue is not empty")
	void isEmptyShouldGiveFalseWhenQueueIsNotEmpty() {
		this.q.enqueue(SOME_ITEM);
		assertFalse(q.isEmpty());
	}

	@Test
	void tstConst() {
		Queue<Integer> queue = new Queue<>();
        assertEquals(0, queue.length());
    }

	@Test
	@DisplayName("enqueue adds the first item to an empty queue")
	void tstEnqueEmpQue() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		assertEquals(1, queue.peek(), "The first item was not added correctly to the queue");
		assertEquals(1, queue.length(), "The length of the queue after first enqueue is incorrect");
	}

	@Test
	@DisplayName("enqueue adds an item to a non-empty queue")
	void tstEnqueToNonEmpQue() {
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    assertEquals(1, queue.peek(), "The first item should still be the first enqueued item");
    assertEquals(2, queue.length(), "The length of the queue after enqueuing to a non-empty queue is incorrect");
	}

	@Test
	@DisplayName("enqueue throws exception when maximum size is reached")
	void tstEnqueAtMaxLen() {
    Queue<Integer> queue = new Queue<>(1); // Queue with max length of 1
    queue.enqueue(1);
    assertThrows(RuntimeException.class, () -> queue.enqueue(2), 
                 "enqueue did not throw an exception when max length was exceeded");
	}

	@Test
	@DisplayName("enqueue works for queues with default and custom max sizes")
	void tstEnqueCstmMxSze() {
    Queue<Integer> defaultQueue = new Queue<>(); // No size limit
    defaultQueue.enqueue(1);
    assertEquals(1, defaultQueue.length(), "Default queue length after enqueue is incorrect");

    Queue<Integer> customQueue = new Queue<>(2); // Max size of 2
    customQueue.enqueue(1);
    customQueue.enqueue(2);
    assertEquals(2, customQueue.length(), "Custom queue length after enqueue is incorrect");
    assertThrows(RuntimeException.class, () -> customQueue.enqueue(3), 
                 "enqueue did not throw an exception when exceeding custom max size");
	}	

	@Test
	@DisplayName("enqueue maintains FIFO order in the queue")
	void tstEnqueFIFO() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		assertEquals(1, queue.dequeue(), "The first dequeued item is not the first enqueued item");
		assertEquals(2, queue.dequeue(), "The second dequeued item is not the second enqueued item");
		assertEquals(3, queue.dequeue(), "The third dequeued item is not the third enqueued item");
	}



	@Test
	@DisplayName("Test for Dequeue")
	void tstDeQue() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		assertEquals(1, queue.dequeue());
		assertEquals(1, queue.length());
	}
	
	@Test
	@DisplayName("Test for peek")
	void tstPeek() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		assertEquals(1, queue.peek());
		assertEquals(1, queue.length());
	}
	
	@Test
	@DisplayName("Test for length")
	void tstLng() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		assertEquals(2, queue.length());
	}
	
	@Test
	@DisplayName("Test for empty")
	void tstEmp() {
		Queue<Integer> queue = new Queue<>();
		assertTrue(queue.isEmpty());
		queue.enqueue(1);
		assertFalse(queue.isEmpty());
	}
	
	@Test
	@DisplayName("Test for remove all")
	void tstRmv() {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.removeAll();
		assertTrue(queue.isEmpty());
	}
	
	@Test
	void tstDeQueThrowsException() {
		Queue<Integer> queue = new Queue<>();
		assertThrows(NoSuchElementException.class, queue::dequeue);
	}
	
	
}