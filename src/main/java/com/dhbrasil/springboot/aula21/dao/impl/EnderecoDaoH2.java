package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDaoH2 implements IDao<Endereco> {

    private ConfigJDBC configJDBC;

    public EnderecoDaoH2() {
        this.configJDBC = new ConfigJDBC();
    }

    // CRUD

    // Salvar
    @Override
    public Endereco salvar(Endereco endereco) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = String.format("INSERT INTO enderecos " +
                "(rua, numero, cidade, bairro, estado) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                endereco.setId(keys.getInt(1));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    // Buscar por ID

    // Buscar todos os registros
    @Override
    public List<Endereco> buscarTodos() {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = "SELECT * FROM enderecos;";
        List<Endereco> enderecos = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                enderecos.add(criarObjetoEndereco(rs));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    // Atualizar

    // Excluir
    @Override
    public void excluir(Integer id) {
        Connection conn = configJDBC.connectToDB();
        Statement st = null;
        String query = String.format("DELETE FROM enderecos WHERE " +
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

    private Endereco criarObjetoEndereco(ResultSet rs) throws SQLException {
        return new Endereco(
                rs.getInt("id"),
                rs.getString("rua"),
                rs.getString("numero"),
                rs.getString("cidade"),
                rs.getString("bairro"),
                rs.getString("estado")
        );
    }

}
