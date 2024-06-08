package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Disciplina;

public class DisciplinaDao implements IDao<Disciplina> {
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public DisciplinaDao (){
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(Disciplina objeto) {
        int registrosAfetados = 0;
        String sql = "DELETE FROM disciplina WHERE nomeDisciplina = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNomeDisciplina());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public List<Disciplina> get() {
        List<Disciplina> registros = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Disciplina registro = new Disciplina();
                registro.setNomeDisciplina(rs.getString("nomeDisciplina"));
                registro.setCargaHoraria(rs.getString("cargaHoraria"));
                registros.add(registro);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return registros;
    }

    @Override
    public Disciplina get(int id) {
        Disciplina registro = null;
        String sql = "SELECT * FROM disciplina WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro = new Disciplina();
                registro.setNomeDisciplina(rs.getString("nomeDisciplina"));
                registro.setCargaHoraria(rs.getString("cargaHoraria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public List<Disciplina> get(String termoBusca) {
        List<Disciplina> registros = new ArrayList<>();
        String sql = "SELECT * FROM disciplina WHERE nomeDisciplina LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                Disciplina registro = new Disciplina();
                registro.setNomeDisciplina(rs.getString("nomeDisciplina"));
                registro.setCargaHoraria(rs.getString("cargaHoraria"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public int insert(Disciplina objeto) {
        int registrosAfetados = 0;
        String sql = "INSERT INTO disciplina (nomeDisciplina, cargaHoraria) VALUES (?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNomeDisciplina());
            ps.setString(2, objeto.getCargaHoraria());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public int update(Disciplina objeto) {
        int registrosAfetados = 0;
        String sql = "UPDATE disciplina SET cargaHoraria = ? WHERE nomeDisciplina = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getCargaHoraria());
            ps.setString(2, objeto.getNomeDisciplina());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }
}
