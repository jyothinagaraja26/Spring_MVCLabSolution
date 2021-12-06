package com.greatlearning.studentmanagmentforfest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.studentmanagmentforfest.entities.Students;

@Service

public interface StudentService {
 
	public List<Students> findAll();

	public Students findById(int theId);

	public void save(Students theStudent);

	public void deleteById(int theId);
	
}
