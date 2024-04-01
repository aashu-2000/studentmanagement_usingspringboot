package com.student.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_details")
public class Student {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	private String student_fname;
	private String student_lname;
	private LocalDate student_DOB;
	private int student_roll;
	
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getStudent_fname() {
		return student_fname;
	}
	public void setStudent_fname(String student_fname) {
		this.student_fname = student_fname;
	}
	public String getStudent_lname() {
		return student_lname;
	}
	public void setStudent_lname(String student_lname) {
		this.student_lname = student_lname;
	}
	public LocalDate getStudent_DOB() {
		return student_DOB;
	}
	public void setStudent_DOB(LocalDate student_DOB) {
		this.student_DOB = student_DOB;
	}
	public int getStudent_roll() {
		return student_roll;
	}
	public void setStudent_roll(int student_roll) {
		this.student_roll = student_roll;
	}
	
	
}
