package com.dhbrasil.springboot.aula21;

import com.dhbrasil.springboot.aula21.dao.impl.DentistaDaoH2;
import com.dhbrasil.springboot.aula21.model.Dentista;
import com.dhbrasil.springboot.aula21.service.DentistaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Aula21ServicesDentistas {

	private static DentistaService dentistaService = new DentistaService(
			new DentistaDaoH2());

	@Test
	void contextLoads() {
	}

	@Test
	public void carregarTresDentistas() {
		Dentista d1 = new Dentista(
				"Pedro", "pramos@gmail.com", 21547, 0);
		Dentista d2 = new Dentista(
				"Marta", "msilveira@outlook.com", 12539, 1);
		Dentista d3 = new Dentista(
				"Marcia", "marcia.soares@hotmail.com", 44127, 1);

		dentistaService.salvar(d1);
		dentistaService.salvar(d2);
		dentistaService.salvar(d3);
	}

	@Test
	public void listarTodosOsDentistas() {
		List<Dentista> dentistasList = dentistaService.buscarTodos();
		System.out.println(dentistasList);
	}

}
