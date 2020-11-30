package com.service.address.business;

import static com.service.address.Builders.AddressDomainBuilder.createAddressDomain;
import static com.service.address.Builders.AddressRequestBuilder.createAddressReq;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.service.address.api.payload.request.AddressRequest;
import com.service.address.api.payload.response.AddressResponse;
import com.service.address.data.AddressDomain;
import com.service.address.data.AddressRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AddressServiceTest {


  @Mock
  private AddressRepository addressRepository;

  @InjectMocks
  private AddressService addressService;

  @BeforeEach
  void setUp(){
    this.addressService = new AddressService();
    openMocks(this);
  }

  @Test
  void whenCreateAddressThenShouldReturnAddressResponseSuccessfully(){
    AddressRequest addressRequest = createAddressReq();
    AddressDomain addressDomain = createAddressDomain();

    when(addressRepository.save(any())).thenReturn(addressDomain);

    AddressResponse addressResponse = this.addressService.createAddress(addressRequest);

    assertNotNull(addressResponse.getId());
    assertEquals(addressDomain.getId(), addressResponse.getId());

  }

  @Test
  void whenFindAllAddressesThenShouldReturnListAddressResponseSuccessfully(){
    AddressDomain addressDomain = createAddressDomain();
    AddressDomain addressDomain1 = createAddressDomain();
    List<AddressDomain> addressDomainsList = new ArrayList<>();
    addressDomainsList.add(addressDomain);
    addressDomainsList.add(addressDomain1);


    when(addressRepository.findAll()).thenReturn(addressDomainsList);

    List<AddressResponse> addressResponse = this.addressService.getAllAddress();

    assertFalse(addressResponse.isEmpty());
  }

  @Test
  void whenFindAddressByIdThenShouldReturnAddressResponseSuccessfully(){
    AddressDomain addressDomain = createAddressDomain();

    when(addressRepository.findById(anyInt())).thenReturn(Optional.of(addressDomain));

    AddressResponse addressResponse = this.addressService.getAddressById(1);

    assertNotNull(addressResponse.getId());
    assertEquals(addressDomain.getId(), addressResponse.getId());
  }

  @Test
  void whenFindAddressByNonExistingIdThenShouldReturnEntityNotFoundException(){

    when(addressRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThatThrownBy(() -> this.addressService.getAddressById(1))
        .isInstanceOf(EntityNotFoundException.class
        );
  }

  @Test
  void whenUpdateAddressByIdThenShouldReturnAddressResponseSuccessfully(){
    AddressRequest addressRequest = createAddressReq();
    AddressDomain addressDomain = createAddressDomain();

    when(addressRepository.findById(anyInt())).thenReturn(Optional.of(addressDomain));
    when(addressRepository.save(any())).thenReturn(addressDomain);

    AddressResponse addressResponse = this.addressService.updateAddress(1, addressRequest);

    assertNotNull(addressResponse.getId());
    assertEquals(addressDomain.getId(), addressResponse.getId());
  }

  @Test
  void whenUpdateAddressByNonExistingIdThenShouldReturnEntityNotFoundException(){
    AddressRequest addressRequest = createAddressReq();
    when(addressRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThatThrownBy(() -> addressService.updateAddress(1, addressRequest))
        .isInstanceOf(EntityNotFoundException.class
        );
  }

  @Test
  void whenDeleteAddressByIdThenShouldReturnAddressResponseSuccessfully(){
    AddressDomain addressDomain = createAddressDomain();

    when(addressRepository.findById(anyInt())).thenReturn(Optional.of(addressDomain));

    this.addressService.deleteAddress(1);
  }

  @Test
  void whenDeleteAddressByNonExistingIdThenShouldReturnEntityNotFoundException(){
    when(addressRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThatThrownBy(() -> this.addressService.deleteAddress(1))
        .isInstanceOf(EntityNotFoundException.class
        );
  }
}
