package com.example;

import java.sql.Connection;
import java.util.List;

import com.example.dao.AlunoDao;
import com.example.dao.ConexaoDB;
import com.example.model.Aluno;

public class App 
{
    public static void main( String[] args )
    {
        ConexaoDB conexao = new ConexaoDB();
        Connection instancia = conexao.getConexao();
        if (instancia != null) {
            System.out.println("Conectou");
        } else {
            System.out.println("Falhou");
        }

        AlunoDao aDao = new AlunoDao();
        List<Aluno> listaAlunos = aDao.get();
        System.out.println("Lista de Alunos");
        for (Aluno item : listaAlunos) {
            System.out.println(item.getNome() + "|" + item.getSexo()+ "|" + item.getIdade() + "|" + item.isEstaAtivo());
        }
        System.out.println("Um aluno por idade");
        Aluno alu = aDao.get(19);
        System.out.println(alu.getNome() + "|" + alu.getSexo()+ "|" + alu.getIdade() + "|" + alu.isEstaAtivo());


    }
}
