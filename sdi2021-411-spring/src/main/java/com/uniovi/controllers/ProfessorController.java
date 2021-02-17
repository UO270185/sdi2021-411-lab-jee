package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@RestController
public class ProfessorController {

	@Autowired 
	private ProfessorService professorService;

	@RequestMapping("professor/list")
	public String getList() {
		return professorService.getProfessors().toString();
	}

	@RequestMapping(value = "/professor/add", method = RequestMethod.POST)
	public String setProfessor(@ModelAttribute Professor professor) {
		professorService.addProfessor(professor);
		return "Added professor "+professor.getName() + " "+professor.getSurname()+", with dni "+professor.getDni()+" and occupation "+professor.getOccupation();
	}

	@RequestMapping("/professor/details/{dni}")
	public String getDetail(@PathVariable String dni) {
		return professorService.getProfessor(dni).toString();
	}

	@RequestMapping("/professor/delete/{dni}")
	public String deleteProfessor(@PathVariable String dni) {
		professorService.deleteProfessor(dni);
		return "Deleting professor"; 
	}
}
