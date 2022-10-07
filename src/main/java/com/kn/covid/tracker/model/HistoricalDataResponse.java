package com.kn.covid.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalDataResponse {
	
	@JsonProperty("All")
	private HistoricalData data;

	@JsonProperty("All")
	public HistoricalData getData() {
		return data;
	}
	
	@JsonProperty("All")
	public void setData(HistoricalData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "HistoricalDataResponse [data=" + data + "]";
	}
	
	
	
}
