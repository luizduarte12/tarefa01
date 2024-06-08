package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.model.AlunoDisciplina;

public class AlunoDisciplinaDao implements IDao<AlunoDisciplina>{
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlunoDisciplinaDao(){
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(AlunoDisciplina objeto) {
        int registrosAfetados = 0;
        String sql = "DELETE FROM AlunoDisciplina WHERE nome = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setObject(1, objeto.getAluno());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public List get() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AlunoDisciplina get(int idade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List get(String termoBusca) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insert(AlunoDisciplina objeto) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(AlunoDisciplina objeto) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
}
