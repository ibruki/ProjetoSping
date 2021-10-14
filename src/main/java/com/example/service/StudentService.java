package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getById(Long id){
        return studentRepository.findById(id);
    }

    public Student getByFirstNameAndLastName(String firstName, String lastName){
        //return studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return studentRepository.getByLastNameAndFirstName(firstName, lastName);
    }

    public List<Student> getByFirstNameOrLastName(String firstName, String lastName){
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest){
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    public List<Student> getAllStudents(){
         return studentRepository.findAll();
    }

    public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);

        return studentRepository.findAll(pageRequest).getContent();
    }

    public Student createStudent (CreateStudentRequest createStudentRequest){
         return studentRepository.save(new Student(createStudentRequest));
    }

    public List<Student> getAllStudentsWithSorting(String sort, String sortOrder){

        if(sortOrder.equals(Sort.Direction.ASC.name())) return studentRepository.findAll(Sort.by(Sort.Direction.ASC, sort));
        else if(sortOrder.equals(Sort.Direction.DESC.name())) return studentRepository.findAll(Sort.by(Sort.Direction.DESC, sort));
        else throw new IllegalArgumentException("Direção inválida!");
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

    public Integer updateStudentWithJpql(Long id, String firstName){
        return studentRepository.updateFirstName(id, firstName);
    }

    public String deleteStudent(long id){
        studentRepository.deleteById(id);
        return "Estudante foi apagado com sucesso.";
    }

    public List<Student> getByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getAllLike(String firstName) {
        return studentRepository.findByFirstNameContains(firstName);
    }

    public List<Student> getAllWhoStartsWith(String firstName) {
        return studentRepository.findByFirstNameStartsWith(firstName);
    }
}
