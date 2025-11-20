package br.com.fiap.bo;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.dao.UsuarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {
    UsuarioDAO usuarioDAO;
    Connection conexao;

    public UsuarioBO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoFactory().conexao();
    }

    public ArrayList<Usuario> selecionarTodosBO() throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();
        try {
            return usuarioDAO.selecionarUsuarios(conexao);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.selecionarUsuarios(conexao);
                }
            }
            throw e;
        }
    }

    public Usuario selecionarUsuarioPorEmailBO(String email) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();
        try {
            return usuarioDAO.selecionarUsuarioPorEmail(conexao, email);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.selecionarUsuarioPorEmail(conexao, email);
                }
            }
            throw e;
        }
    }

    public Usuario selecionarUsuarioPorIdBO(int id) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();
        try {
            return usuarioDAO.selecionarUsuarioPorId(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.selecionarUsuarioPorId(conexao, id);
                }
            }
            throw e;
        }
    }

    public int cadastrarUsuarioBO(Usuario usuario) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();

        try {
            return usuarioDAO.cadastrarUsuario(conexao, usuario);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.cadastrarUsuario(conexao, usuario);
                }
            }
            throw e;
        }
    }

    public boolean atualizarUsuarioBO(Usuario usuario) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();

        try {
            return usuarioDAO.atualizarUsuario(conexao, usuario);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.atualizarUsuario(conexao, usuario);
                }
            }
            throw e;
        }
    }

    public boolean atualizarEmailUsuarioBO(int id_usuario, String email) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();

        try {
            return usuarioDAO.atualizarEmailUsuario(conexao, id_usuario, email);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.atualizarEmailUsuario(conexao, id_usuario, email);
                }
            }
            throw e;
        }
    }

    public boolean atualizarSenhaUsuarioBO(int id_usuario, String senha) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();

        try {
            return usuarioDAO.atualizarSenhaUsuario(conexao, id_usuario, senha);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.atualizarSenhaUsuario(conexao, id_usuario, senha);
                }
            }
            throw e;
        }
    }

    public boolean atualizarPreferenciaUsuarioBO(int id_usuario, String preferencia) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();

        try {
            return usuarioDAO.atualizarPreferenciaUsuario(conexao, id_usuario, preferencia);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    return usuarioDAO.atualizarPreferenciaUsuario(conexao, id_usuario, preferencia);
                }
            }
            throw e;
        }
    }

    public void deletarUsuarioBO(int id) throws SQLException, ClassNotFoundException {
        usuarioDAO = new UsuarioDAO();

        try {
            usuarioDAO.deletarUsuario(conexao, id);
        } catch (Exception e) {
            if (e instanceof SQLException sqlExcecao){
                if (sqlExcecao.getErrorCode() == 17008) {
                    this.conexao.close();
                    this.conexao = new ConexaoFactory().conexao();
                    usuarioDAO.deletarUsuario(conexao, id);
                }
            }
            throw e;
        }
    }
}
