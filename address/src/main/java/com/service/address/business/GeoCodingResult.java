package com.service.address.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoCodingResult {
  @JsonProperty(value = "formatted_address")
  String formattedAddress;

  @JsonProperty("place_id")
  String placeId;

  public String getFormattedAddress() {
    return formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  @Override
  public String toString() {
    return "GeoCodingResult [formattedAddress=" + formattedAddress + ", placeId=" + placeId + "]";
  }
}
