package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public interface StudnetRepository  extends JpaRepository<Student, Integer>{

	@Query("select count(s) from Student s where s.student_roll = ?1 ")
	public int isStudentPresent(int roll);
}
