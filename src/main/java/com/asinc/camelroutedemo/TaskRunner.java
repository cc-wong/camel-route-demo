package com.asinc.camelroutedemo;

import java.util.Optional;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asinc.camelroutedemo.task.TaskResult;

import lombok.extern.slf4j.Slf4j;

/**
 * The task runner.
 */
@Component
@Slf4j
public class TaskRunner {

	/** The camel context. */
	@Autowired
	private CamelContext camelContext;

	/** The producer template. */
	@Autowired
	private ProducerTemplate producerTemplate;

	/**
	 * Runs a specified task.
	 *
	 * @param taskId    the task id
	 * @param requestor the requestor name
	 * @return the task result
	 * @throws IllegalArgumentException if {@code taskId} does not correspond to an
	 *                                  existing task
	 */
	public TaskResult runTask(String taskId, String requestor) {
		Route route = Optional.ofNullable(camelContext.getRoute(taskId)).orElseThrow(
				() -> new IllegalArgumentException(String.format("Route with ID [%s] does not exist!", taskId)));

		Endpoint endpoint = route.getEndpoint();
		log.debug(String.format("Sending request for task [%s]. Endpoint: %s", taskId, endpoint));
		return (TaskResult) producerTemplate.sendBody(endpoint, ExchangePattern.InOut, requestor);
	}

}
