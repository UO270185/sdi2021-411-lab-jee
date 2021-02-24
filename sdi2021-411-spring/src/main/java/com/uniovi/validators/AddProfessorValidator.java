package com.uniovi.validators;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class AddProfessorValidator implements Validator {

	@Autowired
	private ProfessorService professorService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Professor.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor professor = (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (professor.getDni().length() != 9
				&& !Character.isLetter(professor.getDni().charAt(professor.getDni().length() - 1))) {
			errors.rejectValue("dni", "Error.signup.dni.length");
		}
		if (professorService.getProfessorByDni(professor.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (professor.getName().length() < 5 || professor.getName().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (professor.getSurname().length() < 5 || professor.getSurname().length() > 24) {
			errors.rejectValue("apellidos", "Error.signup.lastName.length");
		}
		if (professor.getOccupation().length() < 5 || professor.getSurname().length() > 24) {
			errors.rejectValue("categoria", "Error.signup.categoria.length");
		}
	}
}