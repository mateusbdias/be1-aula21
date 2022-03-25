package com.dhbrasil.springboot.aula21;

import com.dhbrasil.springboot.aula21.dao.impl.PacienteDaoH2;
import com.dhbrasil.springboot.aula21.model.Endereco;
import com.dhbrasil.springboot.aula21.model.Paciente;
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

	@Disabled
	@Test
	public void carregarTresPacientes() {
		Endereco e1 = new Endereco(
				"Av. Eldorado", "445", "Centro",
				"São Paulo", "SP");
		Endereco e2 = new Endereco(
				"Av. Bento Gonçalves", "25", "Centro",
				"Porto Alegre", "RS");
		Endereco e3 = new Endereco(
				"Rua Gomes Jardim", "1254", "Santana",
				"Porto Alegre", "RS");


		Paciente p1 = new Paciente(
				"Carlos", "Moraes",
				"12365498785", new Date(), e1);
		Paciente p2 = new Paciente(
				"Antonio", "Magalhães",
				"63947393784", new Date(), e2);
		Paciente p3 = new Paciente(
				"Marcos", "Silva",
				"29057834434", new Date(), e3);

		pacienteService.salvar(p1);
		pacienteService.salvar(p2);
		pacienteService.salvar(p3);
	}

	@Disabled
	@Test
	public void buscarPacienteComId3() {
		Optional<Paciente> p = pacienteService.buscar(3);
		System.out.println(p);
	}

	@Disabled
	@Test
	public void excluirPacienteComId3() {
		pacienteService.excluir(3);
	}

	@Disabled
	@Test
	public void listarTodosOsPacientes() {
		List<Paciente> pacientesList = pacienteService.buscarTodos();
		System.out.println(pacientesList);
	}

	@Disabled
	@Test
	public void atualizarPacienteComId3() {
		Endereco endAt = new Endereco(
				3, "Av. Paraguassú", "541", "Centro",
				"Capão da Canoa", "RS");
		Paciente pacAt = new Paciente(
				3, "Marcelo", "Gonçalves de Souza",
				"21447888888", endAt);
		Paciente pacUp = pacienteService.atualizar(pacAt);
		System.out.println(pacUp);
	}

}
