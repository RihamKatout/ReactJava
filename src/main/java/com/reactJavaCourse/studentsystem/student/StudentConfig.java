package com.reactJavaCourse.studentsystem.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {


            // save to DB
            repository.saveAll(
                    List.of(new Student(
                            1L,
                            "Riham Katout",
                            "rihamkatm@gmail.com",
                            LocalDate.of(2002, Month.NOVEMBER, 21)
                            ),
                            new Student(
                            "Siwar Katout",
                            "rihamkatm@gmail.com",
                            LocalDate.of(2021, Month.JANUARY, 27)
                            )
                    )
            );
        };
    }
}
