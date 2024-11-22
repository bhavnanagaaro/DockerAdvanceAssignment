// Java Program to Illustrate PersonServiceImpl File

// Importing package module to this code
package com.example.dockerdemo.service;
import java.util.List;
import java.util.Objects;

import com.example.dockerdemo.entity.Person;
import com.example.dockerdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

// Annotation
@Service

// Class
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CacheManager cacheManager;


	public void clearCache() {
		cacheManager.getCache("myCache").clear(); // Clears the entire cache
		System.out.println("Cache 'myCache' has been cleared programmatically!");
	}


	// Save operation
	@Override
	@CachePut(value = "person", key = "#person.id")
	@CacheEvict(value = "personListCache", allEntries = true)
	public Person savePerson(Person person)
	{
		if (person == null) {
			throw new IllegalArgumentException("Person cannot be null");
		}

		// Save the person first, so that the ID is generated
		person = personRepository.save(person);  // Ensure person is persisted, so ID is generated

		return person;
	}

	// Read operation
	@Override
	@Cacheable(value = "personListCache")
	public List<Person> fetchPersonList()
	{
		return (List<Person>)
			personRepository.findAll();
	}

	// Update operation
	@Override
	@CachePut(value = "person", key = "#PersonId")
	@CacheEvict(value = "personListCache", allEntries = true)
	public Person
	updatePerson(Person person,
					Long PersonId)
	{
		Person depDB
			= personRepository.findById(PersonId)
				.get();

		if (Objects.nonNull(person.getPersonName())
			&& !"".equalsIgnoreCase(
				person.getPersonName())) {
			depDB.setPersonName(
				person.getPersonName());
		}

		if (Objects.nonNull(
				person.getPersonAddress())
			&& !"".equalsIgnoreCase(
				person.getPersonAddress())) {
			depDB.setPersonAddress(
				person.getPersonAddress());
		}

		if (Objects.nonNull(person.getPersonCode())
			&& !"".equalsIgnoreCase(
				person.getPersonCode())) {
			depDB.setPersonCode(
				person.getPersonCode());
		}

		return personRepository.save(depDB);
	}

	// Delete operation
	@Override
	@Caching(evict = {
			@CacheEvict(value = "person", key = "#PersonId", beforeInvocation = true),
			@CacheEvict(value = "personListCache", allEntries = true)
	})
	public void deletePersonById(Long PersonId)
	{
		personRepository.deleteById(PersonId);
	}
}
