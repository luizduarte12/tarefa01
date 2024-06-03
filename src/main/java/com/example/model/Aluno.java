package com.example.model;

public class Aluno {
    private String nome;
    private int idade;
    private char sexo;
    private boolean estaAtivo;
    public Aluno(){
        
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public boolean isEstaAtivo() {
        return estaAtivo;
    }
    public void setEstaAtivo(boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }
}
