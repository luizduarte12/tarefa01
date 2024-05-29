package com.example;

import java.sql.Connection;

import com.example.dao.ConexaoDB;

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
    }
}
