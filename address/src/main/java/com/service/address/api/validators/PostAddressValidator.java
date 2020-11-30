package com.service.address.api.validators;

import com.service.address.api.payload.request.AddressRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PostAddressValidator {

  public List<String> validateFields(AddressRequest postRequest) {
    List<String> errorList = new ArrayList<>();

    if (!StringUtils.hasText(postRequest.getStreetName())) {
      errorList.add("The field 'streetName' is mandatory");
    }
    if (!StringUtils.hasText(postRequest.getNumber())) {
      errorList.add("The field 'number' is mandatory");
    }
    if (!StringUtils.hasText(postRequest.getNeighbourhood())) {
      errorList.add("The field 'neighbourhood' is mandatory");
    }
    if (!StringUtils.hasText(postRequest.getCity())) {
      errorList.add("The field 'city' is mandatory");
    }
    if (!StringUtils.hasText(postRequest.getState())) {
      errorList.add("The field 'state' is mandatory");
    }
    if (!StringUtils.hasText(postRequest.getCountry())) {
      errorList.add("The field 'country' is mandatory");
    }
    if (!StringUtils.hasText(postRequest.getZipcode())) {
      errorList.add("The field 'zipCode' is mandatory");
    }
    return errorList;
  }


}
