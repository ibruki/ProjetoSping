package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getById/{id}")
    public StudentResponse getById(@PathVariable Long id){
        return new StudentResponse(studentService.getById(id).stream().findFirst().get());
    }

    @GetMapping("getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName (@PathVariable String firstName){
        List<Student> studentList = studentService.getByFirstName(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(student -> studentResponseList.add(new StudentResponse((student))));
        return studentResponseList;
    }

    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName,
                                                     @PathVariable String lastName) {
        return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
    }

    @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName,
                                                    @PathVariable String lastName){
        List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(student -> studentResponseList.add(new StudentResponse(student)));
        return studentResponseList;
    }

    @GetMapping("getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(student -> studentResponseList.add(new StudentResponse(student)));
        return studentResponseList;
    }


    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(student -> studentResponseList.add(new StudentResponse((student))));

        return studentResponseList;
    }

    @GetMapping("/getAllPagination")
    public List<StudentResponse> getAllStudentsWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
        List<Student> studentList = studentService.getAllStudentsWithPagination(pageNo, pageSize);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse((student))));

        return studentResponseList;
    }

    @GetMapping("/getAllSorted")
    public List<StudentResponse> getAllStudentsWithSorting(@RequestParam String sort, @RequestParam String sortOrder){
        List<Student> studentList = studentService.getAllStudentsWithSorting(sort, sortOrder);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse((student))));

        return studentResponseList;
    }

    @PostMapping("/create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        Student student = studentService.createStudent(createStudentRequest);

        return new StudentResponse(student);
    }

    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        Student student = studentService.updateStudent(updateStudentRequest);

        return new StudentResponse(student);
    }

//    @DeleteMapping("/delete")              DELETE POR PARAMETRO
//    public String deleteStudent(@RequestParam long id){
//        return studentService.deleteStudent(id);
//    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }


}
