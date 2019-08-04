package com.asinc.camelroutedemo;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asinc.camelroutedemo.task.TaskResult;

/**
 * The unit tests on {@link com.asinc.camelroutedemo.ApplicationRunner}.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationRunnerUnitTest {

	/** The task runner. */
	@Mock
	private TaskRunner taskRunner;

	/** The application runner. */
	@InjectMocks
	private ApplicationRunner runner;

	/** The argument captor for the task IDs . */
	@Captor
	private ArgumentCaptor<String> taskIdCaptor;

	/**
	 * Test case on {@link ApplicationRunner#run(String...)} where program arguments
	 * are provided.
	 */
	@Test
	public void testRun_WithArguments() {
		String[] arguments = new String[] { "task1", "task3", "INVALIDTASK", "task2" };
		when(taskRunner.runTask(anyString(), anyString())).thenReturn(initSuccessfulTaskResult())
				.thenReturn(initSuccessfulTaskResult())
				.thenThrow(new IllegalArgumentException("[INVALIDTASK] is not a valid task ID."))
				.thenReturn(initSuccessfulTaskResult());
		List<String> expectedTaskIds = Arrays.asList(arguments);
		testRun(arguments, expectedTaskIds, "Universe");
	}

	/**
	 * Test case on {@link ApplicationRunner#run(String...)} where program arguments
	 * are not provided.
	 */
	@Test
	public void testRun_WithoutArguments() {
		when(taskRunner.runTask(anyString(), anyString())).thenReturn(initSuccessfulTaskResult());
		testRun(new String[0], Arrays.asList("task1", "task2", "task3"), "World");
	}

	/**
	 * Runs a test case on {@link ApplicationRunner#run(String...)}.
	 *
	 * @param arguments         the arguments
	 * @param expectedTaskIds   the expected task IDs
	 * @param expectedRequestor the expected requestor name
	 */
	private void testRun(String[] arguments, List<String> expectedTaskIds, String expectedRequestor) {
		runner.run(arguments);
		verify(taskRunner, times(expectedTaskIds.size())).runTask(taskIdCaptor.capture(), eq(expectedRequestor));
		Assert.assertEquals(expectedTaskIds, taskIdCaptor.getAllValues());
	}

	/**
	 * Initializes a successful task result object.
	 *
	 * @return the task result
	 */
	private TaskResult initSuccessfulTaskResult() {
		return new TaskResult("Success.");
	}

}
