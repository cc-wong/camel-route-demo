package com.asinc.camelroutedemo.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.Setter;

/**
 * Task #3.
 */
@Component
@ConfigurationProperties(prefix = BaseTask.PROPERTY_KEY_PREFIX + "task-3")
public class Task3 extends BaseTask {

	/** The message to return in the task result. */
	@Setter
	@NonNull
	private String successMessage;

	/**
	 * Run task.
	 *
	 * @param requestor the requestor's name
	 * @return the message configured to {@link #successMessage}
	 */
	@Override
	protected String runTask(String requestor) {
		return successMessage;
	}

}
