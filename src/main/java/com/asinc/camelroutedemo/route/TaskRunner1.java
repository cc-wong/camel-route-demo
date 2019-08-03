package com.asinc.camelroutedemo.route;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskRunner1 extends BaseTaskRunner {

	@Override
	protected String getRouteId() {
		return "taskRunner1";
	}

	@Override
	protected void runTask(String name) {
		log.info(String.format("Hello %s!", name));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			log.warn("Sleep interrupted: " + e);
		}
		log.info("Bye~");
	}

}
