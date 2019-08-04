package com.asinc.camelroutedemo.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Task #3.
 */
@Component
@ConfigurationProperties(prefix = BaseTask.PROPERTY_KEY_PREFIX + "task-3")
public class Task3 extends BaseTask {

	/**
	 * Run task.
	 *
	 * @param requestor the requestor's name
	 * @return "Task 3."
	 */
	@Override
	protected String runTask(String requestor) {
		return "Task 3.";
	}

}
