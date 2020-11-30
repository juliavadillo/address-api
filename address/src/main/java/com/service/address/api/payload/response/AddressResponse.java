package com.service.address.api.payload.response;

import com.service.address.data.AddressDomain;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressResponse {

  private Integer id;

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

  public static AddressResponse valueOf(AddressDomain address) {
    return AddressResponse.builder()
        .id(address.getId())
        .streetName(address.getStreetName())
        .number(address.getNumber())
        .complement(address.getComplement())
        .neighbourhood(address.getNeighbourhood())
        .city(address.getCity())
        .state(address.getState())
        .country(address.getCountry())
        .zipcode(address.getZipcode())
        .latitude(address.getLatitude())
        .longitude(address.getLongitude())
        .build();
  }
}
