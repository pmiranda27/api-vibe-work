package br.com.fiap.bo;

import br.com.fiap.beans.EventoCalendario;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.EventoCalendarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventoCalendarioBO {
    private EventoCalendarioDAO eventoCalendarioDAO;
    private Connection conexao;

    public EventoCalendarioBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<EventoCalendario> selecionarTodosBO() throws SQLException, ClassNotFoundException {
        eventoCalendarioDAO = new EventoCalendarioDAO();
        try {
            return eventoCalendarioDAO.selecionarEventosCalendario(conexao);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return eventoCalendarioDAO.selecionarEventosCalendario(conexao);
                }
            }
            throw e;
        }
    }

    public ArrayList<EventoCalendario> selecionarTodosPorIdUsuario(int id) throws SQLException, ClassNotFoundException {
        eventoCalendarioDAO = new EventoCalendarioDAO();
        try {
            return eventoCalendarioDAO.selecionarEventosCalendarioPorIdUsuario(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return eventoCalendarioDAO.selecionarEventosCalendarioPorIdUsuario(conexao, id);
                }
            }
            throw e;
        }
    }

    public int criarEventoCalendarioBO(EventoCalendario eventoCalendario) throws SQLException, ClassNotFoundException {
        eventoCalendarioDAO = new EventoCalendarioDAO();
        try {
            return eventoCalendarioDAO.criarEventoCalendario(conexao, eventoCalendario);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return eventoCalendarioDAO.criarEventoCalendario(conexao, eventoCalendario);
                }
            }
            throw e;
        }
    }

    public void atualizarEventoCalendarioBO(EventoCalendario eventoCalendario) throws SQLException, ClassNotFoundException {
        eventoCalendarioDAO = new EventoCalendarioDAO();
        try {
            if (eventoCalendario.getTitulo().isEmpty()){
                eventoCalendario.setTitulo(eventoCalendarioDAO.selecionarEventosCalendarioPorId(conexao, eventoCalendario.getId()).getTitulo());
            }
            if (eventoCalendario.getDescricao().isEmpty()){
                eventoCalendario.setDescricao(eventoCalendarioDAO.selecionarEventosCalendarioPorId(conexao, eventoCalendario.getId()).getDescricao());
            }
            eventoCalendarioDAO.atualizarEventoCalendario(conexao, eventoCalendario);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    eventoCalendarioDAO.atualizarEventoCalendario(conexao, eventoCalendario);
                }
            }
            throw e;
        }
    }

    public void deletarEventoCalendarioBO(int id) throws SQLException, ClassNotFoundException {
        eventoCalendarioDAO = new EventoCalendarioDAO();
        try {
            eventoCalendarioDAO.deletarEventoCalendario(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    eventoCalendarioDAO.deletarEventoCalendario(conexao, id);
                }
            }
            throw e;
        }
    }
}
