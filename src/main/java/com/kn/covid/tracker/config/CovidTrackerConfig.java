package com.kn.covid.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.kn.covid.tracker.constant.CovidTrackerConstants;

@Configuration
public class CovidTrackerConfig {

	/**
	 * 
	 * @return WebClient with base url
	 */
	@Bean
	public WebClient webClient() {
		return WebClient.builder().baseUrl(CovidTrackerConstants.BASE_URI).build();
	}

	/*
	 * private static ExchangeFilterFunction logResponse() { return
	 * ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
	 * System.out.println("Response status: {}"+ clientResponse.statusCode());
	 * return Mono.just(clientResponse); }); }
	 */
}
