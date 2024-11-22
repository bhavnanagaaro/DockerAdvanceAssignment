// Java Program to Illustrate PersonController File

// Importing package module to this code
package com.example.dockerdemo.controller;


import java.util.List;
// Importing required classes
import com.example.dockerdemo.entity.Person;
import com.example.dockerdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
public class PersonController {

	// Annotation
	@Autowired
	 private PersonService personService;

	// Save operation
	@PostMapping("/addperson")
	public Person savePerson(
		 @RequestBody Person Person)
	{
		return personService.savePerson(Person);
	}

	// Read operation
	@GetMapping("/getAllPersons")
	public List<Person> fetchPersonList()
	{
		return personService.fetchPersonList();
	}

	// Update operation
	@PutMapping("/getPerson/{id}")
	public Person
	updatePerson(@RequestBody Person Person,
					@PathVariable("id") Long PersonId)
	{
		return personService.updatePerson(
			Person, PersonId);
	}

	// Delete operation
	@DeleteMapping("/person/{id}")
	public String deletePersonById(@PathVariable("id")
									Long PersonId)
	{
		personService.deletePersonById(
			PersonId);
		return "Deleted Successfully";
	}
}
