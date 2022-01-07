package com.example.springdemo.core.controller;

import com.example.springdemo.core.entity.Student;
import com.example.springdemo.core.request.CreateStudentRequest;
import com.example.springdemo.core.request.InQueryRequest;
import com.example.springdemo.core.request.UpdateStudentRequest;
import com.example.springdemo.core.response.StudentResponse;
import com.example.springdemo.core.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

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

        studentList.forEach(student -> studentResponseList.add(new StudentResponse((student))));
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

        studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));
        return studentResponseList;
    }

    @GetMapping("getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));
        return studentResponseList;
    }

    @GetMapping("/getByCity/{city}")
    public List<StudentResponse> getByCity(@PathVariable String city){
        List<Student> studentList = studentService.getByCity(city);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));
        return studentResponseList;
    }


    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents(){
//j

        List<Student> studentList = studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse((student))));

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

    @GetMapping("/like/{firstName}")
    public List<StudentResponse> getAllLike(@PathVariable String firstName){
        List<Student> studentList = studentService.getAllLike(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));

        return studentResponseList;
    }

    @GetMapping("/startsWith/{firstName}")
    public List<StudentResponse> getStartsWith(@PathVariable String firstName){
        List<Student> studentList = studentService.getAllWhoStartsWith(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.forEach(student -> studentResponseList.add(new StudentResponse(student)));

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

    @PutMapping("/updateFirstName/{id}/{firstName}")
    public String updateStudentWithJpql(@PathVariable Long id, @PathVariable String firstName){
        return studentService.updateStudentWithJpql(id, firstName) + " Student(s) updated";
    }

//    @DeleteMapping("/delete")              DELETE POR PARAMETRO
//    public String deleteStudent(@RequestParam long id){
//        return studentService.deleteStudent(id);
//    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

    @DeleteMapping("/deleteByFirstName/{firstName}")
    public String deleteStudentByFirstName(@PathVariable String firstName){
        return studentService.deleteStudentWithJpql(firstName) + "Student(s) deleted.";
    }

}
