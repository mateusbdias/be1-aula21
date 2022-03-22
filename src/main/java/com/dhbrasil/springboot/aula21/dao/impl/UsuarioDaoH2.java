package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoH2 implements IDao<Usuario> {

    private ConfigJDBC configJDBC;

    public UsuarioDaoH2() {
        this.configJDBC = new ConfigJDBC();
    }

    // CRUD

    // Salvar
    @Override
    public Usuario salvar(Usuario usuario) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = String.format("INSERT INTO usuarios " +
                "(nome, email, senha, acesso) " +
                "VALUES ('%s', '%s', '%s', '%s')",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getAcesso());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                usuario.setId(keys.getInt(1));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // Buscar por ID

    // Buscar todos os registros
    @Override
    public List<Usuario> buscarTodos() {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = "SELECT * FROM usuarios;";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(criarObjetoUsuario(rs));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Atualizar

    // Excluir
    @Override
    public void excluir(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;
        String query = String.format("DELETE FROM usuarios WHERE " +
                "id = '%s';", id);
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //

    private Usuario criarObjetoUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getInt("acesso")
        );
    }

}
