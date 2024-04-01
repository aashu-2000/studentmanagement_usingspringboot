package com.student.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.student.entity.Student;
import com.student.exception.StudentServiceExeption;
import com.student.repository.StudnetRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudnetRepository studnetRepository;
	

	public String isStudentPresent(Student student)
	{
		if(1==studnetRepository.isStudentPresent(student.getStudent_roll()))
		{
			throw new StudentServiceExeption("This Student_data is already present");
		}
		else
		{
			studnetRepository.save(student);
			return "Data is Saved.....!!!";
		}
	}
	
	public Student getStudentById(int roll)
	{
		 Student student = studnetRepository.findById(roll)
				 .orElseThrow(() -> new StudentServiceExeption("Student does not exists...!!"));
		return student;
	}
	
	public List<Student> getAllStudent()
	{
		List<Student> findAll = studnetRepository.findAll();
		return findAll;
	}
	
	public String updateStudentById(Student studentdto,int id)
	{
		Student student = studnetRepository.findById(id)
		.orElseThrow(() -> new StudentServiceExeption("Student does not exists...!!"));
		
		student.setStudent_fname(studentdto.getStudent_fname());
		student.setStudent_lname(studentdto.getStudent_lname());
		student.setStudent_DOB(studentdto.getStudent_DOB());
		student.setStudent_roll(studentdto.getStudent_roll());
		
		studnetRepository.save(student);
		return "Student is updated...!!";
	}
	
	public String deleteStudentById(int id)
	{
		Student student = studnetRepository.findById(id)
		.orElseThrow(() -> new StudentServiceExeption("Student does not exists...!!"));
		studnetRepository.delete(student);
		return "Student data is deleted...!!";
		
	}
	
	public Page<Student> getPageByPage(int page, int size)
	{
		Pageable pagable= PageRequest.of(page,size);
	    return studnetRepository.findAll(pagable);
	}
}
