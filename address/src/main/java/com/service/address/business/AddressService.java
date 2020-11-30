package com.service.address.business;


import com.service.address.api.payload.request.AddressRequest;
import com.service.address.api.payload.response.AddressResponse;
import com.service.address.data.AddressDomain;
import com.service.address.data.AddressRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AddressService {

  @Autowired
  AddressRepository addressRepository;
  @Autowired
  GeoCodingService geoCodingService;

  public AddressResponse createAddress(AddressRequest addressRequest) {
    if (needToGetGeocodingInfo(addressRequest)) {
      HashMap<String, String> geoCodeValues = geoCodingService.getGeoCoding(addressRequest);
      addressRequest.setLatitude(String.valueOf(geoCodeValues.get("lat")));
      addressRequest.setLongitude(String.valueOf(geoCodeValues.get("lng")));
    }
    AddressDomain address = AddressDomain.valueOf(addressRequest);
    return AddressResponse.valueOf(addressRepository.save(address));
  }

  public List<AddressResponse> getAllAddress() {
    return addressRepository.findAll().stream().map(AddressResponse::valueOf).collect(Collectors.toList());
  }

  public AddressResponse getAddressById(Integer id) {
    Optional<AddressDomain> address = addressRepository.findById(id);
    if (address.isEmpty()) {
      throw new EntityNotFoundException();
    }
    return AddressResponse.valueOf(address.get());
  }

  public AddressResponse updateAddress(Integer id, AddressRequest addressRequest) {
    Optional<AddressDomain> addressOpt = addressRepository.findById(id);
    if (addressOpt.isEmpty()) {
      throw new EntityNotFoundException();
    }
    AddressDomain address = addressOpt.get();
    AddressDomain updatedAddress = address.updateAddress(addressRequest);

    return AddressResponse.valueOf(addressRepository.save(updatedAddress));

  }

  public void deleteAddress(Integer id) {
    Optional<AddressDomain> address = addressRepository.findById(id);
    if (address.isEmpty()) {
      throw new EntityNotFoundException();
    }
    addressRepository.delete(address.get());
  }

  private boolean needToGetGeocodingInfo(AddressRequest addressRequest) {
    return !StringUtils.hasText(addressRequest.getLatitude()) & !StringUtils.hasText(addressRequest.getLongitude());
  }
}
