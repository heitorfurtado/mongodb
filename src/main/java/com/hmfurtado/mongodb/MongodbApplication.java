package com.hmfurtado.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Student student = Student.builder()
                    .firstName("Heitor")
                    .lastName("Furtado")
                    .email("heitormf@live.com")
                    .gender(Gender.MALE)
                    .address(Address.builder()
                            .country("Brazil")
                            .postCode("05072000")
                            .city("São Paulo")
                            .build())
                    .favoriteSubjects(Arrays.asList("Music", "Technology", "Computer Science"))
                    .totalSpentInBooks(BigDecimal.valueOf(30.0))
                    .created(LocalDateTime.now())
                    .build();

//            usingMongoTemplateAndQuery(repository, mongoTemplate, student);
            repository.findStudentByEmail("heitormf@live.com").ifPresentOrElse(s -> {
                System.out.println(s + " alredy exists");
            }, () -> {
                System.out.println("Inserting student " + student);
                repository.insert(student);
            });
        };
    }

    private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(String.valueOf("heitormf@live.com")));

        List<Student> students = mongoTemplate.find(query, Student.class);
        if (students.size() > 1) {
            throw new IllegalStateException("found many students with email");
        }
        if (students.isEmpty()) {
            System.out.println("Inserting student " + student);
            repository.insert(student);
        } else {
            System.out.println(student + " alredy exists");
        }
    }

}
