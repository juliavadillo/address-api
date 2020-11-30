package com.service.address.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public class GeoCoding {

  String status;

  @JsonProperty(value="results")
  GeoCodingResult[] geoCodingResults;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public GeoCodingResult[] getGeoCodingResults() {
    return geoCodingResults;
  }

  public void setGeoCodingResults(GeoCodingResult[] geoCodingResults) {
    this.geoCodingResults = geoCodingResults;
  }

  @Override
  public String toString() {
    return "GeoCoding [status=" + status + ", geoCodingResults=" + Arrays.toString(geoCodingResults) + "]";
  }
}
