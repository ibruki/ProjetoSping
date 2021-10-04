package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/get")
    public String getStudent(){
        return "Hello Student";
    }

    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(student -> studentResponseList.add(new StudentResponse((student))));

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

    @GetMapping("getByFirstName/{firstName}")
        public List<StudentResponse> getByFirstName (@PathVariable String firstName){
        List<Student> studentList = studentService.getByFirstName(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(student -> studentResponseList.add(new StudentResponse((student))));
        return studentResponseList;
    }
}
