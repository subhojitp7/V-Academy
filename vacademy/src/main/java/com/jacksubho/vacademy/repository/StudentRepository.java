package com.jacksubho.vacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacksubho.vacademy.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByEmail(String email);
	

}
