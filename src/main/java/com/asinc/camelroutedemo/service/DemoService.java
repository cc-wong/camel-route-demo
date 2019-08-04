package com.asinc.camelroutedemo.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * The demo service class.
 */
@Service
@Slf4j
public class DemoService {

	/**
	 * Sleeps for a specific number of milliseconds.
	 *
	 * @param milliseconds the milliseconds
	 */
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			log.warn("Sleep interrupted: " + e);
		}
	}

}
