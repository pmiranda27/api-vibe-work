package br.com.fiap.beans;

import java.sql.Timestamp;

public class EventoCalendario {
    private int id;
    private String titulo;
    private Timestamp data;
    private String descricao;
    private Usuario usuario;

    public EventoCalendario(int id, String titulo, Timestamp data, String descricao, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public EventoCalendario() {
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Timestamp getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
