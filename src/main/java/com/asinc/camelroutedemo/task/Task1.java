package com.asinc.camelroutedemo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.asinc.camelroutedemo.service.DemoService;

import lombok.extern.slf4j.Slf4j;

/**
 * Task #1.
 */
@Component
@ConfigurationProperties(prefix = BaseTask.PROPERTY_KEY_PREFIX + "task-1")
@Slf4j
public class Task1 extends BaseTask {

	/** The demo service. */
	@Autowired
	private DemoService demoService;

	/**
	 * Run task.
	 *
	 * @param requestor the requestor's name
	 * @return "Hello [requestor]!"
	 */
	@Override
	protected String runTask(String requestor) {
		log.info("Starting Task #1.");
		demoService.sleep(2000);
		return String.format("Hello %s!", requestor);
	}

}
