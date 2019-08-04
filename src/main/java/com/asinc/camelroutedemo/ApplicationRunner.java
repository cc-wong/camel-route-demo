package com.asinc.camelroutedemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.asinc.camelroutedemo.task.TaskResult;

import lombok.extern.slf4j.Slf4j;

/**
 * The application runner.
 */
@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

	/** The task runner. */
	@Autowired
	private TaskRunner taskRunner;

	/**
	 * Run.
	 *
	 * @param args the program arguments
	 */
	@Override
	public void run(String... args) {
		log.info("Starting app runner. Arguments: " + Arrays.stream(args).collect(Collectors.toList()));

		if (args.length > 0) {
			log.debug("Argument identified.");
			Arrays.stream(args).forEach(taskId -> sendTaskRunRequest(taskId, "Universe"));
		} else {
			List<String> routes = Arrays.asList("task1", "task2", "task3");
			routes.forEach(route -> sendTaskRunRequest(route, "World"));
		}

		log.info("Finish!");
	}

	/**
	 * Send task run request.
	 *
	 * @param taskId    the task ID
	 * @param requestor the requestor name
	 */
	private void sendTaskRunRequest(String taskId, String requestor) {
		try {
			TaskResult result = taskRunner.runTask(taskId, requestor);
			log.info(String.format("Result of [%s]: %s", taskId, result));
		} catch (IllegalArgumentException e) {
			log.error(String.format("Failed to run task of ID [%s]: %s", taskId, e));
		}
	}

}
