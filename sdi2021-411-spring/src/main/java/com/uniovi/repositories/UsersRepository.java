package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByDni(String dni);

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.lastName) LIKE LOWER(?1))")
	List<User> searchByNameAndLastname(String searchText);

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.lastName) LIKE LOWER(?1))")
	Page<User> searchByNameAndLastname(Pageable pageable, String searchText);

	Page<User> findAll(Pageable pageable);
}
