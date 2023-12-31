package com.REST.SpringRestsStudents.service;

import com.REST.SpringRestsStudents.exception.StudentAlreadyExistsException;
import com.REST.SpringRestsStudents.exception.StudentNotFoundException;
import com.REST.SpringRestsStudents.model.Student;
import com.REST.SpringRestsStudents.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService implements iStudentService{
    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+" already exists!");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudents(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(()-> new StudentNotFoundException("Sorry, student could not be found."));
    }

    @Override
    public Student getStudentbyId(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Sorry, no student found with this ID."+id));
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, no student found with this ID."+id);
        }
        studentRepository.deleteById(id);

    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
