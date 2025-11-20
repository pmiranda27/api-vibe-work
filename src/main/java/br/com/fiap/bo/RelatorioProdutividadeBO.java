package br.com.fiap.bo;

import br.com.fiap.beans.RelatorioProdutividade;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.RelatorioProdutividadeDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelatorioProdutividadeBO {
    private RelatorioProdutividadeDAO relatorioProdutividadeDAO;
    private Connection conexao;

    public RelatorioProdutividadeBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<RelatorioProdutividade> selecionarTodosBO() throws SQLException, ClassNotFoundException {
        relatorioProdutividadeDAO = new RelatorioProdutividadeDAO();
        try {
            return relatorioProdutividadeDAO.selecionarRelatorios(conexao);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioProdutividadeDAO.selecionarRelatorios(conexao);
                }
            }
            throw e;
        }
    }

    public ArrayList<RelatorioProdutividade> selecionarTodosPorIdUsuarioBO(int id) throws SQLException, ClassNotFoundException {
        relatorioProdutividadeDAO = new RelatorioProdutividadeDAO();
        try {
            return relatorioProdutividadeDAO.selecionarRelatoriosPorIdUsuario(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioProdutividadeDAO.selecionarRelatorios(conexao);
                }
            }
            throw e;
        }
    }

    public int criarRelatorioProdutividadeBO(RelatorioProdutividade relatorioProdutividade) throws SQLException, ClassNotFoundException {
        relatorioProdutividadeDAO = new RelatorioProdutividadeDAO();
        try {
            return relatorioProdutividadeDAO.criarRelatorioProdutividade(conexao, relatorioProdutividade);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioProdutividadeDAO.criarRelatorioProdutividade(conexao, relatorioProdutividade);
                }
            }
            throw e;
        }
    }

    public void atualizarRelatorioProdutividadeBO(RelatorioProdutividade relatorioProdutividade) throws SQLException, ClassNotFoundException {
        relatorioProdutividadeDAO = new RelatorioProdutividadeDAO();
        try {
            relatorioProdutividadeDAO.atualizarRelatorioProdutividade(conexao, relatorioProdutividade);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    relatorioProdutividadeDAO.atualizarRelatorioProdutividade(conexao, relatorioProdutividade);
                }
            }
            throw e;
        }
    }

    public void deletarRelatorioProdutividadeBO(int id) throws SQLException, ClassNotFoundException {
        relatorioProdutividadeDAO = new RelatorioProdutividadeDAO();
        try {
            relatorioProdutividadeDAO.deletarRelatorioProdutividade(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    relatorioProdutividadeDAO.deletarRelatorioProdutividade(conexao, id);
                }
            }
            throw e;
        }
    }
}
