package com.kn.covid.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VaccineData {
	@JsonProperty("administered")
	private Integer administered;
	@JsonProperty("people_vaccinated")
	private Integer peopleVaccinated;
	@JsonProperty("people_partially_vaccinated")
	private Integer peoplePartiallyVaccinated;
	@JsonProperty("country")
	private String country;
	@JsonProperty("population")
	private Integer population;

	@JsonProperty("administered")
	public Integer getAdministered() {
		return administered;
	}

	@JsonProperty("administered")
	public void setAdministered(Integer administered) {
		this.administered = administered;
	}

	@JsonProperty("people_vaccinated")
	public Integer getPeopleVaccinated() {
		return peopleVaccinated;
	}

	@JsonProperty("people_vaccinated")
	public void setPeopleVaccinated(Integer peopleVaccinated) {
		this.peopleVaccinated = peopleVaccinated;
	}

	@JsonProperty("people_partially_vaccinated")
	public Integer getPeoplePartiallyVaccinated() {
		return peoplePartiallyVaccinated;
	}

	@JsonProperty("people_partially_vaccinated")
	public void setPeoplePartiallyVaccinated(Integer peoplePartiallyVaccinated) {
		this.peoplePartiallyVaccinated = peoplePartiallyVaccinated;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("population")
	public Integer getPopulation() {
		return population;
	}

	@JsonProperty("population")
	public void setPopulation(Integer population) {
		this.population = population;
	}

}
