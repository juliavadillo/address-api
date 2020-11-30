package com.service.address.api.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
  private String streetName;

  private String number;

  private String complement;

  private String neighbourhood;

  private String city;

  private String state;

  private String country;

  private String zipcode;

  private String latitude;

  private String longitude;
}
