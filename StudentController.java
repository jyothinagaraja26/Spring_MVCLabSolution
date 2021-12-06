package com.greatlearning.studentmanagmentforfest.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.studentmanagmentforfest.entities.Students;
import com.greatlearning.studentmanagmentforfest.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	//add mapping for"/list"
	
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		
		System.out.println("request recieved");
		//get students list from database
		
		List<Students> theStudents= studentService.findAll();
		//add to the spring model
		
		theModel.addAttribute("Students", theStudents);
		
		
		return "list-students";
		
	}
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind form data
		Students theStudent=new Students();
		
		theModel.addAttribute("Students", theStudent);

		return "student-form";
		
	}
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		//get the student from service
		Students theStudent= studentService.findById(theId);
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("Students", theStudent);
		//send over to our form
		return "student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("name") String name,@RequestParam("department")String department,@RequestParam("country") String country)
	{
		System.out.println(id);
		Students theStudent;
		if(id!=0) {
			theStudent=studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
			
		}
		else
			theStudent=new Students(name,department,country);
		//save the students
		studentService.save(theStudent);
		//use a redirect to prevent duplicate submission
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		//delete the book 
		studentService.deleteById(theId);
		//redirect to/student/list
		return "redirect:/student/list";
	}
	
	
	
	
	

}
