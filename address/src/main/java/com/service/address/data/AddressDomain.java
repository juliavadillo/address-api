package com.service.address.data;

import com.service.address.api.payload.request.AddressRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressDomain {

  @Id
  @GeneratedValue
  private Integer id;

  @Column
  private String streetName;

  @Column
  private String number;

  @Column
  private String complement;

  @Column
  private String neighbourhood;

  @Column
  private String city;

  @Column
  private String state;

  @Column
  private String country;

  @Column
  private String zipcode;

  @Column
  private String latitude;

  @Column
  private String longitude;

  public static AddressDomain valueOf(AddressRequest addressRequest) {
    return AddressDomain.builder().streetName(addressRequest.getStreetName()).number(addressRequest.getNumber())
        .complement(addressRequest.getComplement()).neighbourhood(addressRequest.getNeighbourhood()).city(addressRequest.getCity())
        .state(addressRequest.getState()).country(addressRequest.getCountry()).zipcode(addressRequest.getZipcode())
        .latitude(addressRequest.getLatitude()).longitude(addressRequest.getLongitude()).build();
  }

  public AddressDomain updateAddress(AddressRequest addressReq){
    if (this.shouldUpdateAttribute(this.streetName, addressReq.getStreetName())) {
      this.streetName = addressReq.getStreetName();
    }
    if (this.shouldUpdateAttribute(this.number, addressReq.getNumber())) {
      this.number = addressReq.getNumber();
    }
    if (this.shouldUpdateAttribute(this.complement, addressReq.getComplement())) {
      this.complement = addressReq.getComplement();
    }
    if (this.shouldUpdateAttribute(this.neighbourhood, addressReq.getNeighbourhood())) {
      this.neighbourhood = addressReq.getNeighbourhood();
    }
    if (this.shouldUpdateAttribute(this.city, addressReq.getCity())) {
      this.city = addressReq.getCity();
    }
    if (this.shouldUpdateAttribute(this.country, addressReq.getCountry())) {
      this.country = addressReq.getCountry();
    }
    if (this.shouldUpdateAttribute(this.zipcode, addressReq.getZipcode())) {
      this.zipcode = addressReq.getZipcode();
    }
    if (this.shouldUpdateAttribute(this.latitude, addressReq.getLatitude())) {
      this.latitude = addressReq.getLatitude();
    }
    if (this.shouldUpdateAttribute(this.longitude, addressReq.getLongitude())) {
      this.longitude = addressReq.getLongitude();
    }
    return this;
  }

  private boolean shouldUpdateAttribute(final String attributeToBeUpdated, final String newAttributeValue) {
    return StringUtils.hasText(newAttributeValue) && !newAttributeValue.equalsIgnoreCase(attributeToBeUpdated);
  }
}
