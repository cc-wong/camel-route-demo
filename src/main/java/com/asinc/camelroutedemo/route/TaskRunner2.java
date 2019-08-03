package com.asinc.camelroutedemo.route;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskRunner2 extends BaseTaskRunner {

	@Override
	protected String getRouteId() {
		return "taskRunner2";
	}

	@Override
	protected void runTask(String name) {
		log.info("Task 2.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			log.warn("Sleep interrupted: " + e);
		}
	}

}
