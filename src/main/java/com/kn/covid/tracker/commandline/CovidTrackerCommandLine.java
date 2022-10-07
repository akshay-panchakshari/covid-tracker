package com.kn.covid.tracker.commandline;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kn.covid.tracker.constant.CovidTrackerConstants;
import com.kn.covid.tracker.exception.AppException;
import com.kn.covid.tracker.model.StatsByCountry;
import com.kn.covid.tracker.service.CovidTrackerService;

@Component
public class CovidTrackerCommandLine implements CommandLineRunner {

	@Autowired
	private CovidTrackerService api;

	/**
	 * Accepts country name as user input and invokes the generateStats
	 */
	@Override
	public void run(String... args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			System.out.print("Enter Country name (For Example:Germany) >> ");
			String str = scanner.nextLine();
			generateStats(str);
			if (str.isBlank()) {
				throw new AppException(CovidTrackerConstants.NO_USER_INPUT);
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			System.out.println("Press CTRL+C to quite the program");
		}

	}

	/**
	 * 
	 * @param country country name for which stats should be displayed
	 */
	private void generateStats(String country) {
		StatsByCountry stats = api.getStatsByCountry(country);
		System.out.printf("--------------- Stats for country >> %s ---------------------", country);
		System.out.println();
		System.out.println("Confirmed cases: " + stats.getConfirmedCases());
		System.out.println("Recovered cases: " + stats.getRecoveredCases());
		System.out.println("Deaths: " + stats.getDeaths());
		System.out.println("Vaccination percentage: " + stats.getVaccinationPercentage());
		System.out.println("New cases since last available data : " + stats.getNewConfirmedCases());

		System.out.println("-------------------------------------------------------------");
	}

}
