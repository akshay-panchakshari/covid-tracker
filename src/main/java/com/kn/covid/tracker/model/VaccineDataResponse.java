package com.kn.covid.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VaccineDataResponse {
	@JsonProperty("All")
	private VaccineData data;

	@JsonProperty("All")
	public VaccineData getData() {
		return data;
	}

	@JsonProperty("All")
	public void setData(VaccineData data) {
		this.data = data;
	}

}
