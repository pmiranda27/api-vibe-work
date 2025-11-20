package br.com.fiap.beans;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String preferenciaTrabalho;

    public Usuario(int id, String nome, String email, String senha, String preferenciaTrabalho) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.preferenciaTrabalho = preferenciaTrabalho;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getPreferenciaTrabalho() {
        return preferenciaTrabalho;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPreferenciaTrabalho(String preferenciaTrabalho) {
        this.preferenciaTrabalho = preferenciaTrabalho;
    }
}
