package com.example.dockerdemo.repository;

import com.example.dockerdemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}