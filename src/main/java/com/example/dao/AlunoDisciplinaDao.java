package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.AlunoDisciplina;
import com.example.model.Disciplina;
import com.example.model.Aluno;

public class AlunoDisciplinaDao implements IDao<AlunoDisciplina> {
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;
    private AlunoDao aDao;
    private DisciplinaDao dDao;

    public AlunoDisciplinaDao() {
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(AlunoDisciplina objeto) {
        int registrosAfetados = 0;
        String sql = "DELETE FROM AlunoDisciplina WHERE nome = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setObject(1, objeto.getAluno().getNome());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public List<AlunoDisciplina> get() {
        List<AlunoDisciplina> registros = new ArrayList<>();
        String sql = "SELECT * from AlunoDisciplina";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AlunoDisciplina registro = new AlunoDisciplina();
                aDao = new AlunoDao();
                Aluno a = (Aluno) aDao.get(rs.getString("idade"));
                registro.setAluno(a);
                dDao = new DisciplinaDao();
                Disciplina d = (Disciplina) dDao.get(rs.getString("nomeDisciplina"));
                registro.setDisciplina(d);
                registros.add(registro);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public AlunoDisciplina get(int idade) {
        AlunoDisciplina registro = null;
        String sql = "SELECT * FROM AlunoDisciplina WHERE idade = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, idade);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro = new AlunoDisciplina();
                aDao = new AlunoDao();
                Aluno a = (Aluno) aDao.get(rs.getString("idade"));
                registro.setAluno(a);
                dDao = new DisciplinaDao();
                Disciplina d = (Disciplina) dDao.get(rs.getString("nomeDisciplina"));
                registro.setDisciplina(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public List<AlunoDisciplina> get(String termoBusca) {
        List<AlunoDisciplina> registros = new ArrayList<>();
        String sql = "SELECT * FROM AlunoDisciplina WHERE nome LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                AlunoDisciplina registro = new AlunoDisciplina();
                aDao = new AlunoDao();
                Aluno a = (Aluno) aDao.get(rs.getString("idade"));
                registro.setAluno(a);
                dDao = new DisciplinaDao();
                Disciplina d = (Disciplina) dDao.get(rs.getString("nomeDisciplina"));
                registro.setDisciplina(d);
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public int insert(AlunoDisciplina objeto) {
        int registrosAfetados = 0;
        String sql = "INSERT INTO AlunoDisciplina (nome, idade, sexo, estaAtivo, nomeDisciplina, cargaHoraria, notaFinal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getAluno().getNome());
            ps.setInt(2, objeto.getAluno().getIdade());
            ps.setString(3, String.valueOf(objeto.getAluno().getSexo()));
            ps.setBoolean(4, objeto.getAluno().isEstaAtivo());
            ps.setString(5, objeto.getDisciplina().getNomeDisciplina());
            ps.setString(6, objeto.getDisciplina().getCargaHoraria());
            ps.setDouble(7, objeto.getNotaFinal());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public int update(AlunoDisciplina objeto) {
        int registrosAfetados = 0;
        String sql = "UPDATE AlunoDisciplina SET nome = ?, idade = ?, sexo = ?, estaAtivo = ?, nomeDisciplina = ?, cargaHoraria = ?, notaFinal = ? WHERE nome = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getAluno().getNome());
            ps.setInt(2, objeto.getAluno().getIdade());
            ps.setString(3, String.valueOf(objeto.getAluno().getSexo()));
            ps.setBoolean(4, objeto.getAluno().isEstaAtivo());
            ps.setString(5, objeto.getDisciplina().getNomeDisciplina());
            ps.setString(6, objeto.getDisciplina().getCargaHoraria());
            ps.setDouble(7, objeto.getNotaFinal());
            ps.setString(8, objeto.getAluno().getNome());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }
}
