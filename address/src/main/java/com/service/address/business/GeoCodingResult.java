package com.service.address.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoCodingResult {
  @JsonProperty(value = "formatted_address")
  String formattedAddress;


  @JsonProperty("geometry")
  LinkedHashMap geometry;





  public String getFormattedAddress() {
    return formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }


  @Override
  public String toString() {
    return "GeoCodingResult [formattedAddress=" + formattedAddress + ", geometry=" + geometry + "]";
  }
}
