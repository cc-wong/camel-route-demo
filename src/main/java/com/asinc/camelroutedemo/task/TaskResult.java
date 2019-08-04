package com.asinc.camelroutedemo.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * The data model for the result of task execution.
 */
@AllArgsConstructor
@Getter
@ToString
public class TaskResult {

	/** The message. */
	@NonNull
	private String message;

}
