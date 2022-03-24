package com.dhbrasil.springboot.aula21;

import com.dhbrasil.springboot.aula21.dao.impl.DentistaDaoH2;
import com.dhbrasil.springboot.aula21.dao.impl.PacienteDaoH2;
import com.dhbrasil.springboot.aula21.model.Dentista;
import com.dhbrasil.springboot.aula21.model.Endereco;
import com.dhbrasil.springboot.aula21.model.Paciente;
import com.dhbrasil.springboot.aula21.service.DentistaService;
import com.dhbrasil.springboot.aula21.service.PacienteService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class Aula21ServicesPacientes {

	private static PacienteService pacienteService = new PacienteService(
			new PacienteDaoH2());

	@Test
	void contextLoads() {
	}

	// @Disabled
	@Test
	public void carregarTresPacientes() {
		Endereco e1 = new Endereco(
				"Av. Eldorado", "445", "Centro",
				"São Paulo", "SP");


		Paciente p1 = new Paciente(
				"Carlos", "Moraes",
				"12365498785", new Date(), e1);

		pacienteService.salvar(p1);
	}

	/*@Disabled
	@Test
	public void listarTodosOsPacientes() {
		List<Paciente> pacientesList = pacienteService.buscarTodos();
		System.out.println(pacientesList);
	}

	@Disabled
	@Test
	public void excluirPacienteComId2() {
		pacienteService.excluir(2);
	}

	@Disabled
	@Test
	public void buscarPacienteComId1() {
		Optional<Paciente> p = pacienteService.buscar(1);
		System.out.println(p);
	}

	@Disabled
	@Test
	public void atualizarPacienteComId3() {
		Paciente pacAt = new Paciente();
		Paciente pacUp = pacienteService.atualizar(pacAt);
		System.out.println(pacUp);
	}*/

}
