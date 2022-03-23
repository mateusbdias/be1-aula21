package com.dhbrasil.springboot.aula21;

import com.dhbrasil.springboot.aula21.dao.impl.EnderecoDaoH2;
import com.dhbrasil.springboot.aula21.model.Endereco;
import com.dhbrasil.springboot.aula21.service.EnderecoService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Aula21ServicesEnderecos {

	private static EnderecoService enderecoService = new EnderecoService(
			new EnderecoDaoH2());

	@Test
	void contextLoads() {
	}

	@Disabled
	@Test
	public void carregarTresEnderecos() {
		Endereco e1 = new Endereco(
				"Av. Paulista", "2000", "São Paulo", "Cerqueira César", "SP");
		Endereco e2 = new Endereco(
				"Rua São Luiz", "396", "Marília", "Centro", "SP");
		Endereco e3 = new Endereco(
				"Rua Rouxinol", "134", "Bombinhas", "Bombas", "SC");

		enderecoService.salvar(e1);
		enderecoService.salvar(e2);
		enderecoService.salvar(e3);
	}

	@Disabled
	@Test
	public void listarTodosOsEnderecos() {
		List<Endereco> enderecosList = enderecoService.buscarTodos();
		System.out.println(enderecosList);
	}

	@Disabled
	@Test
	public void excluirEnderecoComId3() {
		enderecoService.excluir(3);
	}

	@Disabled
	@Test
	public void buscarEnderecoComId1() {
		Optional<Endereco> e = enderecoService.buscar(1);
		System.out.println(e);
	}

	@Disabled
	@Test
	public void atualizarEnderecoComId2() {
		Endereco endAt = new Endereco(
				2, "Av. Sampaio Vidal", "440",
				"Marília", "Centro", "SP");
		Endereco endUp = enderecoService.atualizar(endAt);
		System.out.println(endUp);
	}

}
