package com.service.address.Builders;

import com.service.address.api.payload.request.AddressRequest;
import com.service.address.data.AddressDomain;

public class AddressRequestBuilder {

  public static AddressRequest createAddressReq() {
    return AddressRequest.builder()
        .streetName("Rua das dores")
        .number("7")
        .complement("Sobrado")
        .neighbourhood("Jd santa luz")
        .city("Sumaré")
        .state("São Paulo")
        .country("Brasil")
        .zipcode("01")
        .latitude("02")
        .longitude("03")
        .build();
  }

}
