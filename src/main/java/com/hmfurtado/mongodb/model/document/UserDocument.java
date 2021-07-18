package com.hmfurtado.mongodb.model.document;

import com.hmfurtado.mongodb.model.Enum.GenderEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document
public class UserDocument {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private GenderEnum gender;
    private AddressDocument adress;
    private List<String> musicalGenres;
    private LocalDateTime created;
}