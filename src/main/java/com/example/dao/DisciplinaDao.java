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
    public List get() {
        List<Disciplina> registros = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
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
    public Disciplina get(int idade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List get(String termoBusca) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insert(Disciplina objeto) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Disciplina objeto) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
