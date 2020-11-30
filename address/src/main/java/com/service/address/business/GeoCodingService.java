package com.service.address.business;

import com.service.address.api.payload.request.AddressRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class GeoCodingService {


  @Value("${google.apiKey}")
  private String apiKey;
  private static final String GEOCODING_URI = "https://maps.googleapis.com/maps/api/geocode/json";
  private static final Logger log = LoggerFactory.getLogger(GeoCodingService.class);
  public void getGeoCoding (AddressRequest addressRequest) {

    String address = formattedAddress(addressRequest);
    RestTemplate restTemplate = new RestTemplate();
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GEOCODING_URI)
        .queryParam("address", address)
        .queryParam("key", apiKey);

    log.info("Calling geocoding api with: " + builder.toUriString());

    GeoCoding  geoCoding = restTemplate.getForObject(builder.toUriString(), GeoCoding.class);
  }

  private String formattedAddress(AddressRequest addressRequest) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(addressRequest.getNumber());
    stringBuilder.append(addressRequest.getStreetName());
    return stringBuilder.toString();

  }

}
