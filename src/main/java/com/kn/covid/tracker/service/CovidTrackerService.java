package com.kn.covid.tracker.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kn.covid.tracker.constant.CovidTrackerConstants;
import com.kn.covid.tracker.exception.AppException;
import com.kn.covid.tracker.model.HistoricalData;
import com.kn.covid.tracker.model.HistoricalDataResponse;
import com.kn.covid.tracker.model.RealTimeData;
import com.kn.covid.tracker.model.RealTimeDataResponse;
import com.kn.covid.tracker.model.StatsByCountry;
import com.kn.covid.tracker.model.StatsByCountryBuilder;
import com.kn.covid.tracker.model.VaccineData;
import com.kn.covid.tracker.model.VaccineDataResponse;

import reactor.core.publisher.Mono;

@Service
public class CovidTrackerService {

	@Autowired
	private WebClient webClient;

	/**
	 * Fetches historical covid data for given country from remote API
	 * 
	 * @param country country name for which data needs to be fetched
	 * @return Response from the remote API mapped to HistoricalDataResponse
	 */
	public HistoricalDataResponse getHistoricalData(String country) {

		HistoricalDataResponse historicalData = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(CovidTrackerConstants.HISTORY_URI)
						.queryParam(CovidTrackerConstants.COUNTRY, country)
						.queryParam(CovidTrackerConstants.STATUS, CovidTrackerConstants.CONFIRMED).build())
				.retrieve().bodyToMono(HistoricalDataResponse.class).onErrorMap(throwable -> new AppException(
						CovidTrackerConstants.HISTORY_API_ERROR_MSG + throwable.getMessage(), throwable))
				.block();

		return historicalData;
	}

	/**
	 * Fetches real time covid data for given country from remote API
	 * 
	 * @param country country name for which data needs to be fetched
	 * @return Response from the remote API mapped to RealTimeDataResponse
	 */
	public RealTimeDataResponse getRealTimeData(String country) {
		RealTimeDataResponse realTimeData = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(CovidTrackerConstants.CASES_URI)
						.queryParam(CovidTrackerConstants.COUNTRY, country).build())
				.retrieve().bodyToMono(RealTimeDataResponse.class).onErrorMap(throwable -> new AppException(
						CovidTrackerConstants.RELATIME_API_ERROR_MSG + throwable.getMessage(), throwable))
				.block();
		return realTimeData;
	}

	/**
	 * Fetches vaccination data for given country from remote API
	 * 
	 * @param country country name for which data needs to be fetched
	 * @return Response from the remote API mapped to VaccineDataResponse
	 */
	public VaccineDataResponse getVaccineData(String country) {
		VaccineDataResponse vaccineData = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(CovidTrackerConstants.VACCINES_URI)
						.queryParam(CovidTrackerConstants.COUNTRY, country).build())
				.retrieve().onStatus(HttpStatus::isError, response -> response.bodyToMono(Map.class).flatMap(body -> {
					var message = body.toString();
					return Mono.error(new Exception(message));
				})).bodyToMono(VaccineDataResponse.class).onErrorMap(throwable -> new AppException(
						CovidTrackerConstants.VACCINE_API_ERROR_MSG + throwable.getMessage(), throwable))
				.block();
		return vaccineData;
	}

	/**
	 * Get new confirmed cased since the last available data
	 * 
	 * @param currentCases Current confirmed cases
	 * @param country      Country name for which last available data needs to fetch
	 * @return returns new confirmed cases after calculation or default value
	 */
	public long getNewConfimedCases(long currentCases, String country) {
		Optional<HistoricalData> historicalDataOptional = Optional.ofNullable(getHistoricalData(country).getData());
		return historicalDataOptional.map(historicalData -> calculateNewConfirmedCases(historicalData, currentCases))
				.orElse(0L);
	}

	/**
	 * Logic to calculate new confirmed cases
	 * 
	 * @param historicalData Used to fetch the confirmed case count for latest date
	 * @param currentCases   Current count of confirmed cases
	 * @return returns new confirmed cases since last available data
	 */
	public long calculateNewConfirmedCases(HistoricalData historicalData, long currentCases) {
		Map.Entry<String, Long> latestData = historicalData.getDates().entrySet().stream().findFirst().get();
		// System.out.println("Last available data: " + latestData);
		// System.out.println("Current confimed cases : " + currentCases);
		return currentCases - latestData.getValue();
	}

	/**
	 * Get the Vaccination level for a country in percentage
	 * 
	 * @param country Country name for which Vaccination level needs to be
	 *                calculated
	 * @return Vaccination level in percentage
	 */
	public int getVaccinationPercentage(String country) {
		Optional<VaccineData> vaccineDataOptional = Optional.ofNullable(getVaccineData(country).getData());
		int vaccinationPercantage = 0;
		if (vaccineDataOptional.isPresent()) {
			vaccinationPercantage = calculateVaccinationPercantage(vaccineDataOptional.get());
		} else {
			System.err.println(CovidTrackerConstants.VACCINE_API_INCORRECT_RESPONSE);
		}
		return vaccinationPercantage;
	}

	/**
	 * Logic to vaccination level for a country in percentage
	 * 
	 * @param vaccineData Used to fetch total population and people vaccinated
	 * @return Vaccination level in percentage
	 */
	public int calculateVaccinationPercantage(VaccineData vaccineData) {
		Integer totalPopulation = vaccineData.getPopulation();
		Integer peopleVaccinated = vaccineData.getPeopleVaccinated();
		return peopleVaccinated * 100 / totalPopulation;
	}

	/**
	 * Generates the final output to show on the console
	 * 
	 * @param country country for which data need to display
	 * @return Object holding final output to show in console
	 * @throws AppException
	 */
	public StatsByCountry getStatsByCountry(String country) {
		Optional<RealTimeData> realTimeDataOptional = Optional.ofNullable(getRealTimeData(country).getData());
		RealTimeData realTimeData = realTimeDataOptional
				.orElseThrow(() -> new AppException(CovidTrackerConstants.RELATIME_API_INCORRECT_RESPONSE));

		return new StatsByCountryBuilder().setConfirmedCases(realTimeData.getConfirmed())
				.setRecoveredCases(realTimeData.getRecovered()).setDeaths(realTimeData.getDeaths())
				.setVaccinationPercentage(getVaccinationPercentage(country))
				.setRecoveredCases(getNewConfimedCases(realTimeData.getConfirmed(), country)).build();
	}

}
