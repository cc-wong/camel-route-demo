package com.asinc.camelroutedemo.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Task #3.
 */
@Component
@ConfigurationProperties(prefix = BaseTask.PROPERTY_KEY_PREFIX + "task-3")
@Slf4j
public class Task3 extends BaseTask {

	/**
	 * Run task.
	 *
	 * @param requestor the requestor's name
	 */
	@Override
	protected void runTask(String requestor) {
		log.info("Task 3.");
	}

}
