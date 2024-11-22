// Java Program to Illustrate PersonService File

// Importing package to this code fragment 
package com.example.dockerdemo.service;
// Importing required classes
import com.example.dockerdemo.entity.Person;

import java.util.List;

// Interface
public interface PersonService {

	// Save operation
	Person savePerson(Person person);

	// Read operation
	List<Person> fetchPersonList();

	// Update operation
	Person updatePerson(Person person,
								Long personId);

	// Delete operation
	void deletePersonById(Long PersonId);
}
