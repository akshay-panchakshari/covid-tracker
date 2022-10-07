package com.kn.covid.tracker.model;

public class StatsByCountryBuilder {
	private long confirmedCases;
	private long recoveredCases;
	private long deaths;
	private int vaccinationPercentage;
	private long newConfirmedCases;
	
	public StatsByCountryBuilder() {}
	
	public StatsByCountryBuilder(long confirmedCases, long recoveredCases, long deaths, int vaccinationPercentage,
			long newConfirmedCases) {
		this.confirmedCases = confirmedCases;
		this.recoveredCases = recoveredCases;
		this.deaths = deaths;
		this.vaccinationPercentage = vaccinationPercentage;
		this.newConfirmedCases = newConfirmedCases;
	}
	
	public StatsByCountryBuilder setConfirmedCases(long confirmedCases) {
		this.confirmedCases = confirmedCases;
		return this;
	}
	public StatsByCountryBuilder setRecoveredCases(long recoveredCases) {
		this.recoveredCases = recoveredCases;
		return this;
	}
	public StatsByCountryBuilder setDeaths(long deaths) {
		this.deaths = deaths;
		return this;
	}
	public StatsByCountryBuilder setVaccinationPercentage(int vaccinationPercentage) {
		this.vaccinationPercentage = vaccinationPercentage;
		return this;
	}
	public StatsByCountryBuilder setNewConfirmedCases(long newConfirmedCases) {
		this.newConfirmedCases = newConfirmedCases;
		return this;
	}
	
	public StatsByCountry build() {
		return new StatsByCountry(confirmedCases, recoveredCases, deaths, vaccinationPercentage, newConfirmedCases);
	}
}
