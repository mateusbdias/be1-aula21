package com.dhbrasil.springboot.aula21.dao.impl;

import com.dhbrasil.springboot.aula21.dao.IDao;
import com.dhbrasil.springboot.aula21.dao.config.ConfigJDBC;
import com.dhbrasil.springboot.aula21.model.Paciente;
import com.dhbrasil.springboot.aula21.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PacienteDaoH2 implements IDao<Paciente> {

    private ConfigJDBC configJDBC;
    private EnderecoDaoH2 enderecoDaoH2;

    public PacienteDaoH2() {
        this.configJDBC = new ConfigJDBC();
        this.enderecoDaoH2 = new EnderecoDaoH2();
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        Connection conn = configJDBC.connectToDB();
        PreparedStatement ps = null;

        paciente.setEndereco(enderecoDaoH2.salvar(paciente.getEndereco()));

        String query = String.format("INSERT INTO pacientes " +
                "(nome, sobrenome, cpf, dataCad, id_endereco) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s');",
                paciente.getNome(),
                paciente.getSobrenome(),
                paciente.getCpf(),
                Util.dateToTimestamp(paciente.getDataCad()),
                paciente.getEndereco().getId());

        try {
            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getInt(1));
            ps.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        return Optional.empty();
    }

    @Override
    public void excluir(Integer id) {

    }

    @Override
    public List<Paciente> buscarTodos() {
        return null;
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        return null;
    }


}
