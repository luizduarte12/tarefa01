package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Aluno;

public class AlunoDao implements IDao<Aluno>{
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlunoDao(){
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(Aluno objeto) {
        int registrosAfetados = 0;
        String sql = "DELETE FROM aluno WHERE nome = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public List<Aluno> get() {
       List<Aluno> registros = new ArrayList<>();
       String sql = "SELECT * FROM aluno";
       try {
        ps = conexao.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            Aluno registro = new Aluno();
            registro.setNome(rs.getString("nome"));
            registro.setIdade(rs.getInt("idade"));
            String sexoString = rs.getString("sexo");
            if (sexoString != null && !sexoString.isEmpty()){
                char sexo = sexoString.charAt(0);
                registro.setSexo(sexo);
            }
            registro.setEstaAtivo(rs.getBoolean("estaAtivo"));
            registros.add(registro);
        }
       } catch (SQLException e) {
            e.printStackTrace();
       }
        return registros;
    }

    @Override
    public Aluno get(int idade) {
        Aluno registro = new Aluno();
        String sql = "SELECT * FROM aluno WHERE idade = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idade);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro.setNome(rs.getString("nome"));
                registro.setIdade(rs.getInt("idade"));
                String sexoString = rs.getString("sexo");
                if (sexoString != null && !sexoString.isEmpty()){
                    char sexo = sexoString.charAt(0);
                    registro.setSexo(sexo);
                }
                 registro.setEstaAtivo(rs.getBoolean("estaAtivo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public List<Aluno> get(String termoBusca) {
        List<Aluno> registros = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE nome LIKE ? OR idade LIKE ? OR sexo LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            ps.setString(2, "%" + termoBusca + "%");
            ps.setString(3, "%" + termoBusca + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Aluno registro = new Aluno();
                registro.setNome(rs.getString("nome"));
                registro.setIdade(rs.getInt("idade"));
                String sexoString = rs.getString("sexo");
                if (sexoString != null && !sexoString.isEmpty()){
                    char sexo = sexoString.charAt(0);
                    registro.setSexo(sexo);
                }
                registro.setEstaAtivo(rs.getBoolean("estaAtivo"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public int insert(Aluno objeto) {
        int registrosAfetados = 0;
        String sql = "INSERT INTO aluno (nome,idade,sexo,estaAtivo) VALUES (?,?,?,?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setInt(2, objeto.getIdade());
            ps.setString(3, String.valueOf(objeto.getSexo()));
            ps.setBoolean(4, objeto.isEstaAtivo());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public int update(Aluno objeto) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    
}
