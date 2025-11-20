package br.com.fiap.beans;

import java.time.LocalDate;

public class RelatorioBemEstar {
    private int id;
    private LocalDate data;
    private int nivelEnergia; //1 a 5
    private int quantidadePausas;
    private int nivelEstresse; //1 a 5
    private String descricao;
    private Usuario usuario;

    public RelatorioBemEstar(int id, LocalDate data, int nivelEnergia, int quantidadePausas, int nivelEstresse, String descricao, Usuario usuario) {
        this.id = id;
        this.data = data;
        this.nivelEnergia = nivelEnergia;
        this.quantidadePausas = quantidadePausas;
        this.nivelEstresse = nivelEstresse;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public RelatorioBemEstar() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public int getNivelEnergia() {
        return nivelEnergia;
    }

    public int getQuantidadePausas() {
        return quantidadePausas;
    }

    public int getNivelEstresse() {
        return nivelEstresse;
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setNivelEnergia(int nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    public void setQuantidadePausas(int quantidadePausas) {
        this.quantidadePausas = quantidadePausas;
    }

    public void setNivelEstresse(int nivelEstresse) {
        this.nivelEstresse = nivelEstresse;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
