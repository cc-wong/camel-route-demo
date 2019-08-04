package com.asinc.camelroutedemo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asinc.camelroutedemo.task.TaskResult;

/**
 * The test cases on {@link com.asinc.camelroutedemo.TaskRunner}.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskRunnerUnitTest {

	/** The camel context. */
	@Mock
	private CamelContext camelContext;

	/** The producer template. */
	@Mock
	private ProducerTemplate producerTemplate;

	/** The task runner. */
	@InjectMocks
	private TaskRunner taskRunner;

	/**
	 * Test case on {@link TaskRunner#runTask(String, String)} where the task ID is
	 * valid.
	 */
	@Test
	public void testRunTask_ValidTaskId() {
		Route route = mock(Route.class);
		Endpoint endpoint = mock(Endpoint.class);
		when(route.getEndpoint()).thenReturn(endpoint);
		TaskResult taskResult = mock(TaskResult.class);
		when(producerTemplate.sendBody(any(Endpoint.class), any(ExchangePattern.class), any())).thenReturn(taskResult);

		String requestor = "Requestor Name";
		TaskResult result = testRunTask(route, requestor);
		verify(producerTemplate).sendBody(eq(endpoint), eq(ExchangePattern.InOut), eq(requestor));
		Assert.assertSame(taskResult, result);
	}

	/**
	 * Test case on {@link TaskRunner#runTask(String, String)} where the task ID is
	 * not valid.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRunTask_InvalidTaskId() {
		try {
			testRunTask(null, "Requestor Name");
		} finally {
			verify(producerTemplate, never()).sendBody(any(Endpoint.class), any(ExchangePattern.class), any());
		}
	}

	/**
	 * Runs a test case on {@link TaskRunner#runTask(String, String)}.
	 *
	 * @param route     the route corresponding to the task ID
	 * @param requestor the requestor name
	 * @return the task result
	 */
	private TaskResult testRunTask(Route route, String requestor) {
		String taskId = "task1";
		when(camelContext.getRoute(anyString())).thenReturn(route);
		TaskResult result = taskRunner.runTask(taskId, requestor);
		verify(camelContext).getRoute(eq(taskId));
		return result;
	}

}
