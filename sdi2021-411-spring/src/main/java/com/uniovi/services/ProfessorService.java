package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorService {

	private List<Professor> professorList = new LinkedList<Professor>();

	@PostConstruct
	public void init() {
		professorList.add(new Professor(1L, "71787214M", "Sara", "Rubín", "Estudiante"));
		professorList.add(new Professor(2L, "71787215Y", "Pedro", "Fernández", "Biología"));
	}

	public List<Professor> getProfessors() {
		return professorList;
	}
	
	public Professor getProfessor(String dni){
		return professorList.stream()
		.filter(professor -> professor.getDni().equals(dni)).findFirst().get();
		}


	public void addProfessor(Professor professor) {
		if (professor.getId() == null) {
			professor.setId(professorList.get(professorList.size() - 1).getId() + 1);
		}
		professorList.add(professor);
	}

	public void deleteProfessor(String dni) {
		professorList.removeIf(professor -> professor.getDni().equals(dni));
	}

}
