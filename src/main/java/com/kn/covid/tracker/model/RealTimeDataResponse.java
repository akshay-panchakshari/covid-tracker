package com.kn.covid.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RealTimeDataResponse {
	@JsonProperty("All")
	private RealTimeData data;
	
	@JsonProperty("All")
	public RealTimeData getData() {
		return data;
	}

	@JsonProperty("All")
	public void setData(RealTimeData data) {
		this.data = data;
	}
	
}
