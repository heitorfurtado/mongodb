package com.hmfurtado.mongodb.model.dto;

import com.hmfurtado.mongodb.model.Enum.GenderEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private GenderEnum gender;
    private AddressDTO address;
    private List<String> musicalGenres;
    private LocalDateTime created;

}
