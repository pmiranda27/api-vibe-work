package br.com.fiap.beans;

import java.time.LocalDate;

public class RelatorioProdutividade {
    private int id;
    private Usuario usuario;
    private LocalDate data;
    private double horasTrabalhadas;
    private int qntdDiasPresenciais;
    private int qntdDiasRemotos;
    private String descricao;

    public RelatorioProdutividade(int id, Usuario usuario, LocalDate data, double horasTrabalhadas, int qntdDiasPresenciais, int qntdDiasRemotos, String descricao) {
        this.id = id;
        this.usuario = usuario;
        this.data = data;
        this.horasTrabalhadas = horasTrabalhadas;
        this.qntdDiasPresenciais = qntdDiasPresenciais;
        this.qntdDiasRemotos = qntdDiasRemotos;
        this.descricao = descricao;
    }

    public RelatorioProdutividade() {
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public int getQntdDiasPresenciais() {
        return qntdDiasPresenciais;
    }

    public int getQntdDiasRemotos() {
        return qntdDiasRemotos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public void setQntdDiasPresenciais(int qntdDiasPresenciais) {
        this.qntdDiasPresenciais = qntdDiasPresenciais;
    }

    public void setQntdDiasRemotos(int qntdDiasRemotos) {
        this.qntdDiasRemotos = qntdDiasRemotos;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
