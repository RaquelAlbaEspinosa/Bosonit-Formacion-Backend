package com.bosonit.formacion.block7crudvalidation;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Block7CrudValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);

		PersonaInputDto personaInputDto = new PersonaInputDto();
		personaInputDto.setUsuario("miusuario");
		personaInputDto.setPassword("mipassword");
		personaInputDto.setName("John");
		personaInputDto.setSurname("Doe");
		personaInputDto.setCompanyEmail("john.doe@company.com");
		personaInputDto.setPersonalEmail("johndoe@gmail.com");
		personaInputDto.setCity("Ciudad");
		personaInputDto.setActive(true);
		personaInputDto.setCreatedDate(new Date());
		personaInputDto.setImageUrl("https://example.com/image.jpg");
		personaInputDto.setTerminationDate(null);

		try {
			Persona persona = new Persona(personaInputDto);
			System.out.println(persona);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
