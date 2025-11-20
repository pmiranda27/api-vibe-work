package br.com.fiap.dao;

import br.com.fiap.beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    private int gerarNovoId(Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_usuario) from Usuario");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public Usuario selecionarUsuarioPorEmail(Connection conexao, String email) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Usuario where ds_email = ?");

        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        Usuario usuario = null;
        while (rs.next()){
            usuario = new Usuario();
            usuario.setId(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setPreferenciaTrabalho(rs.getString(5));
        }
        return usuario;
    }

    public ArrayList<Usuario> selecionarUsuarios(Connection conexao) throws SQLException {
        ArrayList<Usuario> listaUsuario = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Usuario");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setPreferenciaTrabalho(rs.getString(5));

            listaUsuario.add(usuario);
        }
        return listaUsuario;
    }

    public Usuario selecionarUsuarioPorId(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Usuario where id_usuario = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        Usuario usuario = new Usuario();

        if(!rs.next()){
            return null;
        }
        else {
            usuario.setId(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setEmail(rs.getString(3));
            usuario.setSenha(rs.getString(4));
            usuario.setPreferenciaTrabalho(rs.getString(5));
        }

        return usuario;
    }

    public int cadastrarUsuario(Connection conexao, Usuario usuario) throws SQLException {
        int novoId = gerarNovoId(conexao);

        PreparedStatement stmt = conexao.prepareStatement("Insert into Usuario (id_usuario, nm_usuario, ds_email, ds_senha, ds_preferencia_trabalho) values (?, ?, ?, ?, ?)");

        stmt.setInt(1, novoId);
        stmt.setString(2, usuario.getNome());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, usuario.getSenha());
        stmt.setString(5, usuario.getPreferenciaTrabalho());

        stmt.execute();

        stmt.close();

        return novoId;
    }

    public boolean atualizarUsuario(Connection conexao, Usuario usuario) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Usuario set nm_usuario = ?, ds_email = ?, ds_senha = ?, ds_preferencia_trabalho = ?  where id_usuario = ?");

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getPreferenciaTrabalho());
        stmt.setInt(5, usuario.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public boolean atualizarEmailUsuario(Connection conexao, int id_usuario, String email) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Usuario set ds_email = ? where id_usuario = ?");

        stmt.setString(1, email);
        stmt.setInt(2, id_usuario);


        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public boolean atualizarSenhaUsuario(Connection conexao, int id_usuario, String senha) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Usuario set ds_senha = ? where id_usuario = ?");

        stmt.setString(1, senha);
        stmt.setInt(2, id_usuario);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public boolean atualizarPreferenciaUsuario(Connection conexao, int id_usuario, String preferencia) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Usuario set ds_preferencia_trabalho = ? where id_usuario = ?");

        stmt.setString(1, preferencia);
        stmt.setInt(2, id_usuario);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public void deletarUsuario(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Delete from Usuario where id_usuario = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }
}
