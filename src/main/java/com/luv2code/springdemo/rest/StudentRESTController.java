package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRESTController {
	
	private List<Student> theStudents;
	
	//define @PostConstruct to load the student data
	
	@PostConstruct
	public void loadData() {
				
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Alex", "Hill"));
		theStudents.add(new Student("Aaron", "Burr"));
		theStudents.add(new Student("Mary", "Smith"));
	}

	// define "/students" - return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return theStudents;
	}
	
	//define for "/students/{studentId}"
	@GetMapping("/students/{studentId}")
	public Student getStudents(@PathVariable int studentId) {
		
		// check studentId against list size
		if (studentId >= theStudents.size() || (studentId < 0)) {
			throw new StudentNotFoundException("Studeng id not found - " + studentId);
		}
		
		return theStudents.get(studentId);
	}
	
}
