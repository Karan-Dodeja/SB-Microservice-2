package com.rest.springboot.controller;

import com.rest.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @GetMapping("")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Test",
                "Test"
        );
        // return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custome-header", "testcustomheader")
                .body(student);
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents () {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Test0","Test0"));
        students.add(new Student(2,"Test1","Test1"));
        students.add(new Student(3,"Test2","Test2"));
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,@PathVariable("first-name") String firstname, @PathVariable("last-name") String lastname) {
        Student student = new Student(studentId, firstname,lastname);
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students/query?id=2&firstName=Test&lastName=test
    @GetMapping("/query")
    public ResponseEntity<Student> studentRequetVariable(@RequestParam int studentId, @RequestParam String firstname, @RequestParam String lastname) {
        Student student = new Student(studentId, firstname,lastname);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> postRequest(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int stuId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent (@PathVariable("id") int id) {
        System.out.println(id);
        return ResponseEntity.ok("deleted Successfully!");
    }

}