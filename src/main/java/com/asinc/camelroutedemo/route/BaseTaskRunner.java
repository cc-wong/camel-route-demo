package com.asinc.camelroutedemo.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public abstract class BaseTaskRunner extends RouteBuilder {

	protected abstract String getRouteId();

	@Override
	public void configure() throws Exception {
		from("direct:" + getRouteId()).routeId(getRouteId()).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				String name = exchange.getIn().getBody(String.class);
				runTask(name);
			}

		});
	}

	protected abstract void runTask(String name);

}