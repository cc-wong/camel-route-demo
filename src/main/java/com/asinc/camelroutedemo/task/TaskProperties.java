package com.asinc.camelroutedemo.task;

import lombok.Data;
import lombok.ToString;

/**
 * The properties of a {@link com.asinc.camelroutedemo.route.BaseTask}.
 */
@Data
@ToString
public class TaskProperties {

	/** The task name. */
	private String taskName;

}
