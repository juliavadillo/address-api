package com.service.address.api.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.service.address.api.payload.request.AddressRequest;
import com.service.address.api.payload.response.AddressResponse;
import com.service.address.api.validators.PostAddressValidator;
import com.service.address.business.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

  @Autowired
  private AddressService addressService;
  @Autowired
  private PostAddressValidator postValidator;

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createAddress(@RequestBody AddressRequest addressRequest) {
    List<String> errorList = postValidator.validateFields(addressRequest);
    if (errorList.isEmpty()) {
      AddressResponse createdAddress = addressService.createAddress(addressRequest);
      return ResponseEntity.ok(createdAddress);
    } else {
      return ResponseEntity.badRequest().body(errorList);
    }
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAllAddress() {
    List<AddressResponse> addressListRes = addressService.getAllAddress();
    if (addressListRes.isEmpty()) {
      ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(addressListRes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getAddressById(@PathVariable(value = "id") Integer id) {
    AddressResponse addressRes = addressService.getAddressById(id);
    return ResponseEntity.ok(addressRes);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Object> updateAddress(@PathVariable(value = "id") Integer id, @RequestBody AddressRequest addressRequest) {
    AddressResponse addressRes = addressService.updateAddress(id, addressRequest);
    return ResponseEntity.ok(addressRes);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteAddress(@PathVariable(value = "id", required = true) Integer id) {
    addressService.deleteAddress(id);
    return ResponseEntity.ok().build();
  }
}
