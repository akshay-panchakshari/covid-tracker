package com.kn.covid.tracker.model;

public class StatsByCountry {
	private long confirmedCases;
	private long recoveredCases;
	private long deaths;
	private int vaccinationPercentage;
	private long newConfirmedCases;
	
	public StatsByCountry(long confirmedCases, long recoveredCases, long deaths, int vaccinationPercentage,
			long newConfirmedCases) {
		this.confirmedCases = confirmedCases;
		this.recoveredCases = recoveredCases;
		this.deaths = deaths;
		this.vaccinationPercentage = vaccinationPercentage;
		this.newConfirmedCases = newConfirmedCases;
	}

	public long getConfirmedCases() {
		return confirmedCases;
	}

	public void setConfirmedCases(long confirmedCases) {
		this.confirmedCases = confirmedCases;
	}

	public long getRecoveredCases() {
		return recoveredCases;
	}

	public void setRecoveredCases(long recoveredCases) {
		this.recoveredCases = recoveredCases;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}

	public int getVaccinationPercentage() {
		return vaccinationPercentage;
	}

	public void setVaccinationPercentage(int vaccinationPercentage) {
		this.vaccinationPercentage = vaccinationPercentage;
	}

	public long getNewConfirmedCases() {
		return newConfirmedCases;
	}

	public void setNewConfirmedCases(long newConfirmedCases) {
		this.newConfirmedCases = newConfirmedCases;
	}
	
}
