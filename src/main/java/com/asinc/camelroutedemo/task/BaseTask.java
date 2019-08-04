package com.asinc.camelroutedemo.task;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The base class for a task.
 */
@Slf4j
public abstract class BaseTask extends RouteBuilder {

	/** The prefix for a property key of a task runner. */
	protected static final String PROPERTY_KEY_PREFIX = "tasks.";

	/** The properties of this task runner. */
	@Setter
	private TaskProperties properties;

	/**
	 * Configure.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void configure() throws Exception {
		log.info(String.format("Configuring task with properties: %s", properties));
		from("direct:" + properties.getTaskName()).routeId(properties.getTaskName()).process(new Processor() {

			/**
			 * Process.
			 *
			 * @param exchange the exchange
			 * @throws Exception the exception
			 */
			@Override
			public void process(Exchange exchange) throws Exception {
				String requestor = exchange.getIn().getBody(String.class);
				String successMessage = runTask(requestor);
				exchange.getOut().setBody(new TaskResult(successMessage));
			}

		});
	}

	/**
	 * Implement to run a task.
	 *
	 * @param requestor the requestor's name
	 * @return a specific message on successfully finishing the task
	 */
	protected abstract String runTask(String requestor);

}