package br.com.fiap.beans;

import br.com.fiap.beans.enums.TipoTrabalho;

public class RotinaSemanal {
    private int id;
    private TipoTrabalho segunda;
    private TipoTrabalho terca;
    private TipoTrabalho quarta;
    private TipoTrabalho quinta;
    private TipoTrabalho sexta;
    private TipoTrabalho sabado;
    private TipoTrabalho domingo;
    private String anotacaoRotina;
    private Usuario usuario;

    public RotinaSemanal(int id, TipoTrabalho segunda, TipoTrabalho terca, TipoTrabalho quarta, TipoTrabalho quinta, TipoTrabalho sexta, TipoTrabalho sabado, TipoTrabalho domingo, String anotacaoRotina, Usuario usuario) {
        this.id = id;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.domingo = domingo;
        this.anotacaoRotina = anotacaoRotina;
        this.usuario = usuario;
    }

    public RotinaSemanal() {
    }

    public int getId() {
        return id;
    }

    public TipoTrabalho getSegunda() {
        return segunda;
    }

    public TipoTrabalho getTerca() {
        return terca;
    }

    public TipoTrabalho getQuarta() {
        return quarta;
    }

    public TipoTrabalho getQuinta() {
        return quinta;
    }

    public TipoTrabalho getSexta() {
        return sexta;
    }

    public TipoTrabalho getSabado() {
        return sabado;
    }

    public TipoTrabalho getDomingo() {
        return domingo;
    }

    public String getAnotacaoRotina() {
        return anotacaoRotina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSegunda(TipoTrabalho segunda) {
        this.segunda = segunda;
    }

    public void setTerca(TipoTrabalho terca) {
        this.terca = terca;
    }

    public void setQuarta(TipoTrabalho quarta) {
        this.quarta = quarta;
    }

    public void setQuinta(TipoTrabalho quinta) {
        this.quinta = quinta;
    }

    public void setSexta(TipoTrabalho sexta) {
        this.sexta = sexta;
    }

    public void setSabado(TipoTrabalho sabado) {
        this.sabado = sabado;
    }

    public void setDomingo(TipoTrabalho domingo) {
        this.domingo = domingo;
    }

    public void setAnotacaoRotina(String anotacaoRotina) {
        this.anotacaoRotina = anotacaoRotina;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
