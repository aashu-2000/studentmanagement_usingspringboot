package com.student.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.Studentdto;
import com.student.entity.Student;
import com.student.exception.StudentServiceExeption;
import com.student.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	

    @Autowired
	private  ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<String> addStudent(@RequestBody Studentdto studentdto)
	{
		Student student = mapper.map(studentdto, Student.class);
		String studentPresent = studentService.isStudentPresent(student);
		return new ResponseEntity<String>(studentPresent, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Studentdto> getStudentById(@PathVariable int id)
	{
	    Student student = studentService.getStudentById(id);
	    Studentdto map = mapper.map(student, Studentdto.class);
	    return new ResponseEntity<Studentdto>(map,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Studentdto studentdto)
	{
		Student map = mapper.map(studentdto, Student.class);
		String msg = studentService.updateStudentById(map, id);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Studentdto>> getAllStudents()
	{
		List<Student> list = studentService.getAllStudent();
		List<Studentdto> collect = list.stream()
				.map(s -> mapper.map(s, Studentdto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<Studentdto>>(collect,HttpStatus.OK);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int id)
	{
		try {
		String deleteStudentById = studentService.deleteStudentById(id);
		return new ResponseEntity<String>(deleteStudentById,HttpStatus.OK);
		}
		catch (StudentServiceExeption e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Studentdto>> getPageByPage(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "3") int size)
	{
		Page<Student> page2 = studentService.getPageByPage(page, size);
		List<Student> content = page2.getContent();
		
		List<Studentdto> list = content.stream()
				.map(s -> mapper.map(s, Studentdto.class)).collect(Collectors.toList());
		return new ResponseEntity<List<Studentdto>>(list,HttpStatus.OK);
	}
}
