package com.REST.SpringRestsStudents.controller;

import com.REST.SpringRestsStudents.exception.StudentNotFoundException;
import com.REST.SpringRestsStudents.model.Student;
import com.REST.SpringRestsStudents.service.StudentService;
import com.REST.SpringRestsStudents.service.iStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

/*    @Autowired
    private StudentService studentService;*/

    private final iStudentService studentsService;
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity(studentsService.getStudents(), HttpStatus.FOUND);
    }
    @GetMapping("/student/{id}")
    public Student getStudentbyId(@PathVariable Long id){
        return studentsService.getStudentbyId(id);
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentsService.addStudent(student);

    }
    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student,@PathVariable Long id){
        return studentsService.updateStudents(student,id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteStudent(@PathVariable Long id) throws StudentNotFoundException{
        studentsService.deleteStudent(id);
    }





}
