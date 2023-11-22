package com.reactJavaCourse.studentsystem.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

//    will have all the resources of the API

    private final StudentService studentService;

    @Autowired // anything passed to constructor should be injected
    public StudentController(StudentService studentService) {
        //we should avoid this and use dependency injection instead
        //this.studentService = new StudentService();
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }


    //when you want to add new resources to your system
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){ // we take the request body and then map it to student here
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = {"studentId"})
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email
                              ){
        studentService.updateStudent(studentId, name, email);
    }
}
