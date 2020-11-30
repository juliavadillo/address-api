package com.service.address.api.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.service.address.api.payload.request.AddressRequest;
import com.service.address.api.payload.response.AddressResponse;
import com.service.address.api.validators.PostAddressValidator;
import com.service.address.business.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Api(value="Address API REST")
@RestController
@RequestMapping(value = "/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @Autowired
  private PostAddressValidator postValidator;

  @ApiOperation(value="This operation creates an Address")
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

  @ApiOperation(value="This operation returns all addresses registered")
  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getAllAddress() {
    List<AddressResponse> addressListRes = addressService.getAllAddress();
    if (addressListRes.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(addressListRes);
  }

  @ApiOperation(value="This operation returns the address related to the given id")
  @GetMapping("/{id}")
  public ResponseEntity<Object> getAddressById(@PathVariable(value = "id") Integer id) {
    AddressResponse addressRes = addressService.getAddressById(id);
    return ResponseEntity.ok(addressRes);
  }

  @ApiOperation(value="This operation updates the address object with the new given values")
  @PatchMapping("/{id}")
  public ResponseEntity<Object> updateAddress(@PathVariable(value = "id") Integer id, @RequestBody AddressRequest addressRequest) {
    AddressResponse addressRes = addressService.updateAddress(id, addressRequest);
    return ResponseEntity.ok(addressRes);
  }

  @ApiOperation(value="This operation deletes the address related to the given id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteAddress(@PathVariable(value = "id", required = true) Integer id) {
    addressService.deleteAddress(id);
    return ResponseEntity.ok().build();
  }
}
