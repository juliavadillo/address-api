package com.service.address.api;

import static com.service.address.Builders.AddressDomainBuilder.createAddressDomain;
import static com.service.address.Builders.AddressRequestBuilder.createAddressReq;
import static com.service.address.Builders.AddressResponseBuilder.createAddressRes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.service.address.api.controllers.AddressController;
import com.service.address.api.payload.request.AddressRequest;
import com.service.address.api.payload.response.AddressResponse;
import com.service.address.api.validators.PostAddressValidator;
import com.service.address.business.AddressService;
import com.service.address.data.AddressDomain;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AddressControllerTest {

  @Mock
  private AddressService addressService;

  @Mock
  private PostAddressValidator postValidator;

  @InjectMocks
  private AddressController addressController;

  @BeforeEach
  void setUp(){
    this.addressController = new AddressController();
    openMocks(this);
  }

  @Test
  void shouldPostAddressSuccessfully() {
    AddressRequest addressRequest = createAddressReq();
    AddressResponse addressDomain = createAddressRes();

    when(postValidator.validateFields(addressRequest))
        .thenReturn(Lists.newArrayList());

    when(addressService.createAddress(addressRequest)).thenReturn(addressDomain);

    final ResponseEntity<Object> responseEntity = this.addressController.createAddress(addressRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());

  }

  @Test
  void shouldPostAddressError() {
      AddressRequest addressRequest = createAddressReq();

      List<String> errorList = new ArrayList<>();
      errorList.add("The field 'streetName' is mandatory");

      when(postValidator.validateFields(addressRequest))
          .thenReturn(errorList);

      final ResponseEntity<Object> responseEntity = this.addressController.createAddress(addressRequest);

      assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
      assertNotNull(responseEntity.getBody());
  }

  @Test
  void shouldGetAddressSuccessfully() {
    AddressResponse addressRes = createAddressRes();
    AddressResponse addressRes1 = createAddressRes();

    List<AddressResponse> addressResponses = new ArrayList<>();
    addressResponses.add(addressRes);
    addressResponses.add(addressRes1);

    when(this.addressService.getAllAddress())
        .thenReturn(addressResponses);

    final ResponseEntity<Object> responseEntity = this.addressController.getAllAddress();

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  void shouldGetAddressEmpty() {

    when(this.addressService.getAllAddress())
        .thenReturn(Lists.emptyList());

    final ResponseEntity<Object> responseEntity = this.addressController.getAllAddress();

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  @Test
  void shouldGetByIdAddressSuccessfully() {
    AddressResponse addressRes = createAddressRes();

    when(addressService.getAddressById(anyInt()))
        .thenReturn(addressRes);

    final ResponseEntity<Object> responseEntity = this.addressController.getAddressById(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
  }

  @Test
  void shouldUpdateAddressSuccessfully() {
    AddressRequest addressRequest = createAddressReq();
    AddressResponse addressRes = createAddressRes();

    when(addressService.updateAddress(anyInt(), any()))
        .thenReturn(addressRes);

    final ResponseEntity<Object> responseEntity = this.addressController.updateAddress(1, addressRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
  }

  @Test
  void shouldDeleteAddressSuccessfully() {

    final ResponseEntity<Object> responseEntity = this.addressController.deleteAddress(1);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }
}