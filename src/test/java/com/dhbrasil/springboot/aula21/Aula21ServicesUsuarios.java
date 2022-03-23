package com.dhbrasil.springboot.aula21;

import com.dhbrasil.springboot.aula21.dao.impl.UsuarioDaoH2;
import com.dhbrasil.springboot.aula21.model.Usuario;
import com.dhbrasil.springboot.aula21.service.UsuarioService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Aula21ServicesUsuarios {

	private static UsuarioService usuarioService = new UsuarioService(
			new UsuarioDaoH2());

	@Test
	void contextLoads() {
	}

	@Disabled
	@Test
	public void carregarTresUsuarios() {
		Usuario u1 = new Usuario(
				"José", "jose@mail.com", "password", 1);
		Usuario u2 = new Usuario(
				"Maria", "maria@mail.com", "123321", 2);
		Usuario u3 = new Usuario(
				"Renata", "renata@mail.com", "qwerty", 4);

		usuarioService.salvar(u1);
		usuarioService.salvar(u2);
		usuarioService.salvar(u3);
	}

	@Disabled
	@Test
	public void listarTodosOsUsuarios() {
		List<Usuario> usuariosList = usuarioService.buscarTodos();
		System.out.println(usuariosList);
	}

	@Disabled
	@Test
	public void excluirUsuarioComId1() {
		usuarioService.excluir(1);
	}

	@Disabled
	@Test
	public void buscarUsuarioComId2() {
		Optional<Usuario> u = usuarioService.buscar(2);
		System.out.println(u);
	}

	@Disabled
	@Test
	public void atualizarUsuarioComId3() {
		Usuario userAt = new Usuario(
				3, "Renata Queiroz", "renata@mail.com",
				"qwerty123", 3);
		Usuario userUp = usuarioService.atualizar(userAt);
		System.out.println(userUp);
	}

}
