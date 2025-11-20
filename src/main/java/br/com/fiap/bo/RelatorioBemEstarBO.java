package br.com.fiap.bo;

import br.com.fiap.beans.RelatorioBemEstar;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.RelatorioBemEstarDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelatorioBemEstarBO {
    private RelatorioBemEstarDAO relatorioBemEstarDAO;
    private Connection conexao;

    public RelatorioBemEstarBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<RelatorioBemEstar> selecionarTodosBO() throws SQLException, ClassNotFoundException {
        relatorioBemEstarDAO = new RelatorioBemEstarDAO();
        try {
            return relatorioBemEstarDAO.selecionarRelatoriosBemEstar(conexao);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioBemEstarDAO.selecionarRelatoriosBemEstar(conexao);
                }
            }
            throw e;
        }
    }

    public ArrayList<RelatorioBemEstar> selecionarTodosPorIdUsuarioBO(int id) throws SQLException, ClassNotFoundException {
        relatorioBemEstarDAO = new RelatorioBemEstarDAO();
        try {
            return relatorioBemEstarDAO.selecionarRelatoriosBemEstarPorIdUsuario(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioBemEstarDAO.selecionarRelatoriosBemEstarPorIdUsuario(conexao, id);
                }
            }
            throw e;
        }
    }

    public int criarRelatorioBemEstarBO(RelatorioBemEstar relatorioBemEstar) throws SQLException, ClassNotFoundException {
        relatorioBemEstarDAO = new RelatorioBemEstarDAO();
        try {
            return relatorioBemEstarDAO.criarRelatorioBemEstar(conexao, relatorioBemEstar);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return relatorioBemEstarDAO.criarRelatorioBemEstar(conexao, relatorioBemEstar);
                }
            }
            throw e;
        }
    }

    public void atualizarRelatorioBemEstarBO(RelatorioBemEstar relatorioBemEstar) throws SQLException, ClassNotFoundException {
        relatorioBemEstarDAO = new RelatorioBemEstarDAO();
        try {
            relatorioBemEstarDAO.atualizarRelatorioBemEstar(conexao, relatorioBemEstar);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    relatorioBemEstarDAO.atualizarRelatorioBemEstar(conexao, relatorioBemEstar);
                }
            }
            throw e;
        }
    }

    public void deletarRelatorioBemEstarBO(int id) throws SQLException, ClassNotFoundException {
        relatorioBemEstarDAO = new RelatorioBemEstarDAO();
        try {
            relatorioBemEstarDAO.deletarRelatorioBemEstar(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    relatorioBemEstarDAO.deletarRelatorioBemEstar(conexao, id);
                }
            }
            throw e;
        }
    }
}
