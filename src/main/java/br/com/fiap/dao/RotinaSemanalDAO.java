package br.com.fiap.dao;

import br.com.fiap.beans.RotinaSemanal;
import br.com.fiap.beans.Usuario;
import br.com.fiap.beans.enums.TipoTrabalho;
import br.com.fiap.bo.RotinaSemanalBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RotinaSemanalDAO {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    private int gerarNovoId(Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_rotina_semanal) from Rotina_Semanal");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public ArrayList<RotinaSemanal> selecionarRotinas(Connection conexao) throws SQLException {
        ArrayList<RotinaSemanal> listaRotinas = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Rotina_Semanal");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            RotinaSemanal rotinaSemanal = new RotinaSemanal();
            rotinaSemanal.setId(rs.getInt(1));
            rotinaSemanal.setSegunda(TipoTrabalho.valueOf(rs.getString(2)));
            rotinaSemanal.setTerca(TipoTrabalho.valueOf(rs.getString(3)));
            rotinaSemanal.setQuarta(TipoTrabalho.valueOf(rs.getString(4)));
            rotinaSemanal.setQuinta(TipoTrabalho.valueOf(rs.getString(5)));
            rotinaSemanal.setSexta(TipoTrabalho.valueOf(rs.getString(6)));
            rotinaSemanal.setSabado(TipoTrabalho.valueOf(rs.getString(7)));
            rotinaSemanal.setDomingo(TipoTrabalho.valueOf(rs.getString(8)));
            rotinaSemanal.setAnotacaoRotina(rs.getString(9));

            rotinaSemanal.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(10)));

            listaRotinas.add(rotinaSemanal);
        }
        return listaRotinas;
    }

    public RotinaSemanal selecionarRotinaSemanalPorIdUsuario(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select * from Rotina_Semanal where fk_id_usuario = ?");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        RotinaSemanal rotinaSemanal = null;
        while (rs.next()){
            rotinaSemanal = new RotinaSemanal();
            rotinaSemanal.setId(rs.getInt(1));
            rotinaSemanal.setSegunda(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(2)));
            rotinaSemanal.setTerca(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(3)));
            rotinaSemanal.setQuarta(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(4)));
            rotinaSemanal.setQuinta(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(5)));
            rotinaSemanal.setSexta(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(6)));
            rotinaSemanal.setSabado(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(7)));
            rotinaSemanal.setDomingo(TipoTrabalho.transformarStringEmEnumTrabalho(rs.getString(8)));
            rotinaSemanal.setAnotacaoRotina(rs.getString(9));
            rotinaSemanal.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, id));

        }
        return rotinaSemanal;
    }

    public int criarRotinaSemanal(Connection conexao, RotinaSemanal rotinaSemanal) throws SQLException {
        int novoId = gerarNovoId(conexao);

        PreparedStatement stmt = conexao.prepareStatement("Insert into Rotina_Semanal (id_rotina_semanal, ds_escala_segunda, ds_escala_terca, ds_escala_quarta, ds_escala_quinta, ds_escala_sexta, ds_escala_sabado, ds_escala_domingo, ds_anotacao_rotina, fk_id_usuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, novoId);
        stmt.setString(2, String.valueOf(rotinaSemanal.getSegunda()));
        stmt.setString(3, String.valueOf(rotinaSemanal.getTerca()));
        stmt.setString(4, String.valueOf(rotinaSemanal.getQuarta()));
        stmt.setString(5, String.valueOf(rotinaSemanal.getQuinta()));
        stmt.setString(6, String.valueOf(rotinaSemanal.getSexta()));
        stmt.setString(7, String.valueOf(rotinaSemanal.getSabado()));
        stmt.setString(8, String.valueOf(rotinaSemanal.getDomingo()));
        stmt.setString(9, rotinaSemanal.getAnotacaoRotina());
        stmt.setInt(10, rotinaSemanal.getUsuario().getId());

        stmt.execute();

        stmt.close();

        return novoId;
    }

    public boolean atualizarTurnosRotinaSemanal(Connection conexao, RotinaSemanal rotinaSemanal) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Rotina_Semanal set ds_escala_segunda = ?, ds_escala_terca = ?, ds_escala_quarta = ?, ds_escala_quinta = ?, ds_escala_sexta = ?, ds_escala_sabado = ?, ds_escala_domingo = ? where id_rotina_semanal = ?");

        stmt.setString(1, String.valueOf(rotinaSemanal.getSegunda()));
        stmt.setString(2, String.valueOf(rotinaSemanal.getTerca()));
        stmt.setString(3, String.valueOf(rotinaSemanal.getQuarta()));
        stmt.setString(4, String.valueOf(rotinaSemanal.getQuinta()));
        stmt.setString(5, String.valueOf(rotinaSemanal.getSexta()));
        stmt.setString(6, String.valueOf(rotinaSemanal.getSabado()));
        stmt.setString(7, String.valueOf(rotinaSemanal.getDomingo()));
        stmt.setInt(8, rotinaSemanal.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public boolean atualizarAnotacaoRotinaSemanal(Connection conexao, RotinaSemanal rotinaSemanal) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Rotina_Semanal set ds_anotacao_rotina = ? where id_rotina_semanal = ?");

        stmt.setString(1, rotinaSemanal.getAnotacaoRotina());
        stmt.setInt(2, rotinaSemanal.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public boolean atualizarDiaRotinaSemanal(Connection conexao, int id, String dia, String turno) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Rotina_Semanal set ds_escala_"+dia+" = ? where id_rotina_semanal = ?");

        TipoTrabalho tipoTurno = TipoTrabalho.transformarStringEmEnumTrabalho(turno);

        stmt.setString(1, tipoTurno.name());
        stmt.setInt(2, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
        else {
            return true;
        }
    }

    public void deletarRotinaSemanal(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Delete from Rotina_Semanal where id_rotina_semanal = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }
}
