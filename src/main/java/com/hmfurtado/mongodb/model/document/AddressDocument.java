package com.hmfurtado.mongodb.model.document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDocument {

    private String country;
    private String postCode;
    private String city;
}
