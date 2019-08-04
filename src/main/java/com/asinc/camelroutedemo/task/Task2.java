package com.asinc.camelroutedemo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.asinc.camelroutedemo.service.DemoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Task #2.
 */
@Component
@ConfigurationProperties(prefix = BaseTask.PROPERTY_KEY_PREFIX + "task-2")
@Slf4j
public class Task2 extends BaseTask {

	/** The demo service. */
	@Autowired
	private DemoService demoService;

	/**
	 * Run task.
	 *
	 * @param requestor the requestor's name
	 * @return "Yo! [requestor]."
	 */
	@Override
	protected String runTask(String requestor) {
		log.info("Task 2.");
		demoService.sleep(500);
		return String.format("Yo! %s.", requestor);
	}

}
