package com.hmfurtado.mongodb.service;

import com.hmfurtado.mongodb.model.document.AddressDocument;
import com.hmfurtado.mongodb.model.document.UserDocument;
import com.hmfurtado.mongodb.model.dto.AddressDTO;
import com.hmfurtado.mongodb.model.dto.UserDTO;
import com.hmfurtado.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> fetchUsers() {
        return userRepository.findAll().stream().map(u -> parseDocumentToPojo(u)).collect(Collectors.toList());
    }

    public void newUser(UserDTO dto) {
        userRepository.findUserDocumentByEmail(dto.getEmail()).ifPresentOrElse(u -> {
            throw new RuntimeException("User alredy exists");
        }, () -> {
            userRepository.save(UserDocument.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .email(dto.getEmail())
                    .gender(dto.getGender())
                    .musicalGenres(dto.getMusicalGenres())
                    .adress(AddressDocument.builder()
                            .city(dto.getAddress().getCity())
                            .country(dto.getAddress().getCountry())
                            .postCode(dto.getAddress().getPostCode())
                            .build())
                    .created(LocalDateTime.now())
                    .build());
        });
    }

    public void deleteUser(String email) {
        userRepository.findUserDocumentByEmail(email).ifPresentOrElse(u -> {
            userRepository.delete(u);
        }, () -> {
            throw new RuntimeException("User dont exists");
        });
    }

    public UserDTO findUserByEmail(String email) {
        return userRepository.findUserDocumentByEmail(email).stream()
                .map(this::parseDocumentToPojo).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDTO parseDocumentToPojo(UserDocument user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .address(parseAddressToPojo(user.getAdress()))
                .musicalGenres(user.getMusicalGenres())
                .created(user.getCreated())
                .build();
    }

    public AddressDTO parseAddressToPojo(AddressDocument address) {
        return AddressDTO.builder()
                .city(address.getCity())
                .postCode(address.getPostCode())
                .country(address.getCountry())
                .build();
    }

}
