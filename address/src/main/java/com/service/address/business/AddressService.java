package com.service.address.business;


import com.service.address.api.payload.request.AddressRequest;
import com.service.address.api.payload.response.AddressResponse;
import com.service.address.data.AddressDomain;
import com.service.address.data.AddressRepository;
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


  public AddressResponse createAddress(AddressRequest addressRequest) {
    AddressDomain address = AddressDomain.valueOf(addressRequest);
    return AddressResponse.valueOf(addressRepository.save(address));
  }

  private boolean needToGetGeocodingInfo(AddressRequest addressRequest) {
    return !StringUtils.hasText(addressRequest.getLatitude()) & !StringUtils.hasText(addressRequest.getLongitude());
  }

  public List<AddressResponse> getAllAddress() {
    List<AddressResponse> addresses = addressRepository.findAll().stream().map(AddressResponse::valueOf).collect(Collectors.toList());
    return addresses;
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
    if (address.isEmpty()){
      throw new EntityNotFoundException();
    }
    addressRepository.delete(address.get());
  }
}
