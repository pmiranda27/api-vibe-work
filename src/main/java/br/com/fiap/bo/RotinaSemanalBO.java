package br.com.fiap.bo;

import br.com.fiap.beans.RotinaSemanal;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.RotinaSemanalDAO;
import br.com.fiap.dao.UsuarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RotinaSemanalBO {
    private RotinaSemanalDAO rotinaSemanalDAO;
    private Connection conexao;

    public RotinaSemanalBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<RotinaSemanal> selecionarTodosBO() throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            return rotinaSemanalDAO.selecionarRotinas(conexao);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return rotinaSemanalDAO.selecionarRotinas(conexao);
                }
            }
            throw e;
        }
    }

    public RotinaSemanal selecionarRotinaSemanalPorIdUsuario(int id) throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            return rotinaSemanalDAO.selecionarRotinaSemanalPorIdUsuario(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return rotinaSemanalDAO.selecionarRotinaSemanalPorIdUsuario(conexao, id);
                }
            }
            throw e;
        }
    }

    public int criarRotinaSemanalBO(RotinaSemanal rotinaSemanal) throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            return rotinaSemanalDAO.criarRotinaSemanal(conexao, rotinaSemanal);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return rotinaSemanalDAO.criarRotinaSemanal(conexao, rotinaSemanal);
                }
            }
            throw e;
        }
    }

    public boolean atualizarTurnosRotinaSemanalBO(RotinaSemanal rotinaSemanal) throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            return rotinaSemanalDAO.atualizarTurnosRotinaSemanal(conexao, rotinaSemanal);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return rotinaSemanalDAO.atualizarTurnosRotinaSemanal(conexao, rotinaSemanal);
                }
            }
            throw e;
        }
    }

    public boolean atualizarAnotacaoRotinaSemanal(RotinaSemanal rotinaSemanal) throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            return rotinaSemanalDAO.atualizarAnotacaoRotinaSemanal(conexao, rotinaSemanal);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return rotinaSemanalDAO.atualizarAnotacaoRotinaSemanal(conexao, rotinaSemanal);
                }
            }
            throw e;
        }
    }

    public boolean atualizarDiaRotinaSemanal(int id, String dia, String turno) throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            return rotinaSemanalDAO.atualizarDiaRotinaSemanal(conexao, id, dia, turno);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return rotinaSemanalDAO.atualizarDiaRotinaSemanal(conexao, id, dia, turno);
                }
            }
            throw e;
        }
    }

    public void deletarRotinaSemanalBO(int id) throws SQLException, ClassNotFoundException {
        rotinaSemanalDAO = new RotinaSemanalDAO();
        try {
            rotinaSemanalDAO.deletarRotinaSemanal(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    rotinaSemanalDAO.deletarRotinaSemanal(conexao, id);
                }
            }
            throw e;
        }
    }
}