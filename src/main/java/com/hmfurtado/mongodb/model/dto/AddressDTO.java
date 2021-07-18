package com.hmfurtado.mongodb.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private String country;
    private String postCode;
    private String city;
}
