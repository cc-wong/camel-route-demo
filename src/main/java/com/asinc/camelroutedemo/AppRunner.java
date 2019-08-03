package com.asinc.camelroutedemo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppRunner implements CommandLineRunner {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private ProducerTemplate producerTemplate;

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting app runner");

		Map<String, Endpoint> endpointMap = camelContext.getEndpointMap();
		log.info("Endpoints: " + endpointMap);

		if (args.length > 0) {
			String routeId = args[0];
			log.info("Argument identified.");
			if (camelContext.getRoute(routeId) == null) {
				log.error(String.format("Route with ID [%s] does not exist!", routeId));
			} else {
				Endpoint endpoint = camelContext.getRoute(routeId).getEndpoint();
				log.info("Endpoint: " + endpoint);
				producerTemplate.sendBody(endpoint, "Universe");
			}
		} else {
			IntStream.rangeClosed(1, 5).forEachOrdered(index -> {
				log.info("Sending request: " + index);
				sendRequest("taskRunner1", "Number " + index);
			});

			List<String> routes = Arrays.asList("taskRunner1", "taskRunner2", "taskRunner3");
			routes.forEach(route -> {
				String name = "World";
				sendRequest(route, name);
			});
		}

		log.info("Finish!");
	}

	private void sendRequest(String routeId, String name) {
		log.info("Sending to route: " + routeId);
		String endpointUri = "direct:" + routeId;
		producerTemplate.sendBody(endpointUri, name);
	}

}
