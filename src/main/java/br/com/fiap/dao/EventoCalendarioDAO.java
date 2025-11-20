package br.com.fiap.dao;

import br.com.fiap.beans.EventoCalendario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventoCalendarioDAO {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    private int gerarNovoId(Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_evento_calendario) from Evento_Calendario");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public ArrayList<EventoCalendario> selecionarEventosCalendario(Connection conexao) throws SQLException {
        ArrayList<EventoCalendario> listaEventos = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Evento_Calendario");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            EventoCalendario eventoCalendario = new EventoCalendario();
            eventoCalendario.setId(rs.getInt(1));
            eventoCalendario.setTitulo(rs.getString(2));
            eventoCalendario.setData(rs.getTimestamp(3));
            eventoCalendario.setDescricao(rs.getString(4));

            eventoCalendario.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(5)));

            listaEventos.add(eventoCalendario);
        }
        return listaEventos;
    }

    public ArrayList<EventoCalendario> selecionarEventosCalendarioPorIdUsuario(Connection conexao, int id_usuario) throws SQLException {
        ArrayList<EventoCalendario> listaEventos = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Evento_Calendario where fk_id_usuario = ?");

        stmt.setInt(1, id_usuario);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            EventoCalendario eventoCalendario = new EventoCalendario();
            eventoCalendario.setId(rs.getInt(1));
            eventoCalendario.setTitulo(rs.getString(2));
            eventoCalendario.setData(rs.getTimestamp(3));
            eventoCalendario.setDescricao(rs.getString(4));

            eventoCalendario.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(5)));

            listaEventos.add(eventoCalendario);
        }
        return listaEventos;
    }

    public int criarEventoCalendario (Connection conexao, EventoCalendario eventoCalendario) throws SQLException {
        int novoId = gerarNovoId(conexao);

        PreparedStatement stmt = conexao.prepareStatement("Insert into Evento_Calendario (id_evento_calendario, ds_titulo, ts_evento, ds_evento, fk_id_usuario) values (?, ?, ?, ?, ?)");

        stmt.setInt(1, novoId);
        stmt.setString(2, eventoCalendario.getTitulo());
        stmt.setTimestamp(3, eventoCalendario.getData());
        stmt.setString(4, eventoCalendario.getDescricao());
        stmt.setInt(5, eventoCalendario.getUsuario().getId());

        stmt.execute();

        stmt.close();

        return novoId;
    }

    public void atualizarEventoCalendario(Connection conexao, EventoCalendario eventoCalendario) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Evento_Calendario set ds_titulo = ?, ds_evento = ? where id_evento_calendario = ?");

        stmt.setString(1, eventoCalendario.getTitulo());
        stmt.setString(2, eventoCalendario.getDescricao());
        stmt.setInt(3, eventoCalendario.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }

    public void deletarEventoCalendario(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Delete from Evento_Calendario where id_evento_calendario = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }
}
