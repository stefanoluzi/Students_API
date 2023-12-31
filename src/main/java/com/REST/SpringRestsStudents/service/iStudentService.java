package com.REST.SpringRestsStudents.service;

import com.REST.SpringRestsStudents.model.Student;

import java.util.List;

public interface iStudentService {
    public Student addStudent(Student student);
    public List<Student> getStudents();
    public Student updateStudents(Student student,Long id);
    public Student getStudentbyId(Long id);
    void deleteStudent(Long id);

}
