package com.asinc.camelroutedemo.task;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import lombok.Getter;

/**
 * The base class for a task.
 */
public abstract class BaseTask extends RouteBuilder {

	/** The prefix for a property key of a task runner. */
	protected static final String PROPERTY_KEY_PREFIX = "tasks.";

	/** The properties of this task runner. */
	@Getter
	private TaskProperties properties = new TaskProperties();

	/**
	 * Configure.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void configure() throws Exception {
		from("direct:" + properties.getTaskName()).routeId(properties.getTaskName()).process(new Processor() {

			/**
			 * Process.
			 *
			 * @param exchange the exchange
			 * @throws Exception the exception
			 */
			@Override
			public void process(Exchange exchange) throws Exception {
				String name = exchange.getIn().getBody(String.class);
				runTask(name);
				exchange.getOut().setBody(new TaskResult("Success."));
			}

		});
	}

	/**
	 * Implement to run a task.
	 *
	 * @param requestor the requestor's name
	 */
	protected abstract void runTask(String requestor);

}