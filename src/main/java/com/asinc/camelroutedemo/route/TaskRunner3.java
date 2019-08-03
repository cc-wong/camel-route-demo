package com.asinc.camelroutedemo.route;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskRunner3 extends BaseTaskRunner {

	@Override
	protected String getRouteId() {
		return "taskRunner3";
	}

	@Override
	protected void runTask(String name) {
		log.info("Task 3.");
	}

}
