package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public Page<Professor> getProfessors(Pageable pageable) {
		Page<Professor> professors = professorRepository.findAll(pageable);
		return professors;
	}
	
	public Professor getProfessor(Long id){
		return professorRepository.findById(id).get();
	}


	public void addProfessor(Professor professor) {
		professorRepository.save(professor);
	}

	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}
	
	public Professor getProfessorByDni(String dni) {
		return professorRepository.findByDni(dni);
	}

}
