package com.reactJavaCourse.studentsystem.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// a class should be instantiated (it has to be a spring bean); Autowired

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student>  studentOptional=studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        System.out.println("student to add: " + student);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean b = studentRepository.existsById(studentId);
        if(!b){
            throw new IllegalStateException("Student with the id: "+studentId+" isn't exist" );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    // the entity goes into a managed state
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow( () -> new IllegalStateException("student with id: "+studentId+"doesn't exist"));

        if(name != null && name.length() > 0
            && !Objects.equals(student.getName(), name))
        {
            student.setName(name);
        }
        if(email != null && email.length() > 0
                && !Objects.equals(student.getEmail(), email))
        {
            Optional <Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if(optionalStudent.isPresent())
                throw new IllegalStateException("email is already taken by another student !!!");
            student.setEmail(email);
        }
    }
}
