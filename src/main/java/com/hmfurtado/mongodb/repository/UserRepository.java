package com.hmfurtado.mongodb.repository;

import com.hmfurtado.mongodb.model.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findUserDocumentByEmail(String email);

}
