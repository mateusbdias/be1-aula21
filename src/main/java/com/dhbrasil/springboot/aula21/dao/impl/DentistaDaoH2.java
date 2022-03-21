package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Dentista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DentistaDaoH2 implements IDao<Dentista> {

    private ConfigJDBC configJDBC;

    public DentistaDaoH2() {
        this.configJDBC = new ConfigJDBC();
    }

    // CRUD

    // Salvar
    @Override
    public Dentista salvar(Dentista dentista) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = String.format("INSERT INTO dentistas " +
                "(nome, email, numMatricula, atendeConvenio) " +
                "VALUES ('%s', '%s', '%s', '%s')",
                dentista.getNome(),
                dentista.getEmail(),
                dentista.getNumMatricula(),
                dentista.getAtendeConvenio());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                dentista.setId(keys.getInt(1));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentista;
    }

    // Buscar por ID

    // Buscar todos os registros
    @Override
    public List<Dentista> buscarTodos() {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        String query = "SELECT * FROM dentistas;";
        List<Dentista> dentistas = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dentistas.add(criarObjetoDentista(rs));
            }
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dentistas;
    }

    // Atualizar

    // Excluir

    //

    private Dentista criarObjetoDentista(ResultSet rs) throws SQLException {
        return new Dentista(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getInt("numMatricula"),
                rs.getInt("atendeConvenio")
        );
    }

}
