package com.kn.covid.tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kn.covid.tracker.constant.CovidTrackerConstants;
import com.kn.covid.tracker.model.HistoricalData;
import com.kn.covid.tracker.model.HistoricalDataResponse;
import com.kn.covid.tracker.model.RealTimeDataResponse;
import com.kn.covid.tracker.model.VaccineData;
import com.kn.covid.tracker.model.VaccineDataResponse;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "10000")
public class CovidTrackerServiceTest {

	private VaccineData vaccineData;
	private HistoricalData historicalData;
	
	public static final String EXPECTED_COUNTRY= "Switzerland";
	public static final int EXPECTED_VACCINATION= 3;
	public static final int EXPECTED_NEW_CASES= 3;
	public static final long CURRENT_CASES= 33652260L;
	
	@Autowired
	CovidTrackerService api;

	@Autowired
	private WebTestClient webTestClient;

	@BeforeEach
	public void setup() {
		vaccineData = new VaccineData();
		vaccineData.setPopulation(64979548);
		vaccineData.setPeopleVaccinated(2297100);

		historicalData = new HistoricalData();
		HashMap<String, Long> data = new HashMap<String, Long>();
		data.put("2022-10-05", 33652255L);
		historicalData.setDates(data);
		webTestClient = WebTestClient.bindToServer().baseUrl(CovidTrackerConstants.BASE_URI).build();

	}

	@Test
	public void getRealTimeData() {
		this.webTestClient.get()
				.uri(uriBuilder -> uriBuilder.path(CovidTrackerConstants.CASES_URI)
						.queryParam(CovidTrackerConstants.COUNTRY, EXPECTED_COUNTRY).build())
				.exchange().expectStatus().isOk().expectBody(RealTimeDataResponse.class).consumeWith(
						response -> assertEquals(EXPECTED_COUNTRY,response.getResponseBody().getData().getCountry()));
	}

	@Test
	public void getHistoricalData() {
		this.webTestClient.get()
				.uri(uriBuilder -> uriBuilder.path(CovidTrackerConstants.HISTORY_URI)
						.queryParam(CovidTrackerConstants.COUNTRY, "EXPECTED_COUNTRY")
						.queryParam(CovidTrackerConstants.STATUS, CovidTrackerConstants.CONFIRMED).build())
				.exchange().expectStatus().isOk().expectBody(HistoricalDataResponse.class).consumeWith(
						response -> assertEquals("EXPECTED_COUNTRY",response.getResponseBody().getData().getCountry()));
	}

	@Test
	public void getVaccineData() {
		this.webTestClient.get()
				.uri(uriBuilder -> uriBuilder.path(CovidTrackerConstants.VACCINES_URI)
						.queryParam(CovidTrackerConstants.COUNTRY, "EXPECTED_COUNTRY").build())
				.exchange().expectStatus().isOk().expectBody(VaccineDataResponse.class).consumeWith(
						response -> assertEquals("EXPECTED_COUNTRY",response.getResponseBody().getData().getCountry() ));
	}

	@Test
	public void calculateVaccinationPercantage() {
		int calculateVaccinationPercantage = api.calculateVaccinationPercantage(vaccineData);
		assertEquals(EXPECTED_VACCINATION, calculateVaccinationPercantage);
	}

	@Test
	public void calculateNewConfirmedCases() {
		long calculateNewConfirmedCases = api.calculateNewConfirmedCases(historicalData, CURRENT_CASES);
		assertEquals(EXPECTED_NEW_CASES, calculateNewConfirmedCases);
	}
	
}
