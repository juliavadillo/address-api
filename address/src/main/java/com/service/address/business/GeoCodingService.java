package com.service.address.business;

import com.service.address.api.payload.request.AddressRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
@Service
public class GeoCodingService {


  @Value("${google.apiKey}")
  private String apiKey;
  private static final String GEOCODING_URI = "https://maps.googleapis.com/maps/api/geocode/json";
  private static final Logger log = LoggerFactory.getLogger(GeoCodingService.class);
  public HashMap<String,String> getGeoCoding (AddressRequest addressRequest) {
 HashMap<String,String> geoCodeValues = new HashMap<>();
    String address = formattedAddress(addressRequest);
    RestTemplate restTemplate = new RestTemplate();
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GEOCODING_URI)
        .queryParam("address", address)
        .queryParam("key", apiKey);

    log.info("Calling geocoding api with: " + builder.toUriString());

    GeoCoding geoCoding = restTemplate.getForObject(builder.toUriString(), GeoCoding.class);
    GeoCodingResult[] result = geoCoding.getGeoCodingResults();
    geoCodeValues = (HashMap<String, String>) result[0].geometry.get("location");

    return geoCodeValues;
  }

  private String formattedAddress(AddressRequest addressRequest) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(addressRequest.getNumber());
    stringBuilder.append("+");
    stringBuilder.append(addressRequest.getStreetName());
    stringBuilder.append(",");
    stringBuilder.append(addressRequest.getNeighbourhood());
    stringBuilder.append(",");
    stringBuilder.append(addressRequest.getState());

    return stringBuilder.toString();

  }

}
