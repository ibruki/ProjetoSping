package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

     public List<Student> getAllStudents(){
         return studentRepository.findAll();
     }

     public Student createStudent (CreateStudentRequest createStudentRequest){
         return studentRepository.save(new Student(createStudentRequest));
     }

     public Student updateStudent(UpdateStudentRequest updateStudentRequest){
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

         if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()){
            student.setFirstName(updateStudentRequest.getFirstName());
        }

        if(updateStudentRequest.getLastName() != null && !updateStudentRequest.getLastName().isEmpty()){
            student.setLastName(updateStudentRequest.getLastName());
        }

        if(updateStudentRequest.getEmail() != null && !updateStudentRequest.getEmail().isEmpty()){
            student.setEmail(updateStudentRequest.getEmail());
        }
        return studentRepository.save(student);
     }

    public String deleteStudent(long id){
        studentRepository.deleteById(id);
        return "Estudante foi apagado com sucesso.";
    }

    public List<Student> getByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }

}
