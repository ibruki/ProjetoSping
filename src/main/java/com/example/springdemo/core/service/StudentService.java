package com.example.springdemo.core.service;

import com.example.springdemo.core.entity.Address;
import com.example.springdemo.core.repository.AddressRepository;
import com.example.springdemo.core.entity.Student;
import com.example.springdemo.core.entity.Subject;
import com.example.springdemo.core.repository.StudentRepository;
import com.example.springdemo.core.repository.SubjectRepository;
import com.example.springdemo.core.request.CreateStudentRequest;
import com.example.springdemo.core.request.CreateSubjectRequest;
import com.example.springdemo.core.request.InQueryRequest;
import com.example.springdemo.core.request.UpdateStudentRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;
    AddressRepository addressRepository;
    SubjectRepository subjectRepository;

    public StudentService (StudentRepository studentRepository, AddressRepository addressRepository, SubjectRepository subjectRepository){
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
        this.subjectRepository = subjectRepository;
    }

    public Optional<Student> getById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> getByCity(String city){
        return studentRepository.findByAddressCity(city);
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

    @Transactional
    public Student createStudent (CreateStudentRequest createStudentRequest){
        Student student = new Student(createStudentRequest);
        student.setAddress
                (addressRepository.save
                        (new Address
                                (createStudentRequest.getStreet(), createStudentRequest.getCity())));

        student = studentRepository.save(student);

        List<Subject> subjectsList = new ArrayList<>();
        if(createStudentRequest.getLearningSubjects() != null) {
            for (CreateSubjectRequest subjectRequest : createStudentRequest.getLearningSubjects()) {
                Subject subject = new Subject();
                subject.setSubject_name(subjectRequest.getSubject_name());
                subject.setMarks_obtained(subjectRequest.getMarksObtained());
                subject.setStudent(student);

                subjectsList.add(subject);
            }
            subjectRepository.saveAll(subjectsList);
        }

        student.setLearningSubjects(subjectsList);

        return student;
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

    public Integer deleteStudentWithJpql(String firstName){
        return studentRepository.deleteByFirstName(firstName);
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
