package com.jacksubho.vacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacksubho.vacademy.entity.Instructor;
import com.jacksubho.vacademy.entity.Student;
import com.jacksubho.vacademy.repository.InstructorRepository;
import com.jacksubho.vacademy.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private InstructorRepository instructorRepo;
	
	@RequestMapping("/showStudentReg")
	public String showStudentRegistrationPage() {
		return "login/registerStudent";
	}

	@RequestMapping("/showStudentLogin")
	public String showStudentLoginPage() {
		return "login/studentLogin";
	}
	
	@RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
	public String register(@ModelAttribute("student") Student student) {
		studentRepo.save(student);
		return "login/studentLogin";
	}
	
	@RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap map) {
		Student student = studentRepo.findByEmail(email);
		List<Instructor> instructors = instructorRepo.findByStudentId(student.getId());
		if(student.getPassword().equals(password)) {
			map.addAttribute("student", student);
			map.addAttribute("instructors", instructors);
			return "/access";
		}else {
			map.addAttribute("msg", "Invalid UserName/Password");
		}
		return "login/studentLogin";
	}

}
