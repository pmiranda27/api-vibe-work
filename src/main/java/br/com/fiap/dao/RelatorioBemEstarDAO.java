package br.com.fiap.dao;

import br.com.fiap.beans.RelatorioBemEstar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RelatorioBemEstarDAO {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    private int gerarNovoId(Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_relatorio_bem_estar) from Relatorio_Bem_Estar");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public ArrayList<RelatorioBemEstar> selecionarRelatoriosBemEstar(Connection conexao) throws SQLException {
        ArrayList<RelatorioBemEstar> listaRelatorios = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Relatorio_Bem_Estar");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            RelatorioBemEstar relatorioBemEstar = new RelatorioBemEstar();
            relatorioBemEstar.setId(rs.getInt(1));
            relatorioBemEstar.setData(rs.getDate(2).toLocalDate());
            relatorioBemEstar.setNivelEnergia(rs.getInt(3));
            relatorioBemEstar.setQuantidadePausas(rs.getInt(4));
            relatorioBemEstar.setNivelEstresse(rs.getInt(5));
            relatorioBemEstar.setDescricao(rs.getString(6));

            relatorioBemEstar.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(7)));

            listaRelatorios.add(relatorioBemEstar);
        }
        return listaRelatorios;
    }

    public ArrayList<RelatorioBemEstar> selecionarRelatoriosBemEstarPorIdUsuario(Connection conexao, int id_usuario) throws SQLException {
        ArrayList<RelatorioBemEstar> listaRelatorios = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Relatorio_Bem_Estar where fk_id_usuario = ?");

        stmt.setInt(1, id_usuario);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            RelatorioBemEstar relatorioBemEstar = new RelatorioBemEstar();
            relatorioBemEstar.setId(rs.getInt(1));
            relatorioBemEstar.setData(rs.getDate(2).toLocalDate());
            relatorioBemEstar.setNivelEnergia(rs.getInt(3));
            relatorioBemEstar.setQuantidadePausas(rs.getInt(4));
            relatorioBemEstar.setNivelEstresse(rs.getInt(5));
            relatorioBemEstar.setDescricao(rs.getString(6));

            relatorioBemEstar.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(7)));

            listaRelatorios.add(relatorioBemEstar);
        }
        return listaRelatorios;
    }

    public int criarRelatorioBemEstar (Connection conexao, RelatorioBemEstar relatorioBemEstar) throws SQLException {
        int novoId = gerarNovoId(conexao);

        PreparedStatement stmt = conexao.prepareStatement("Insert into Relatorio_Bem_Estar (id_relatorio_bem_estar, dt_relatorio_bem_estar, pt_energia, qtd_pausas, pt_estresse, ds_relatorio_bem_estar, fk_id_usuario) values (?, ?, ?, ?, ?, ?, ?)");

        LocalDate dataAgora = LocalDate.now();

        stmt.setInt(1, novoId);
        stmt.setDate(2, java.sql.Date.valueOf(dataAgora));
        stmt.setInt(3, relatorioBemEstar.getNivelEnergia());
        stmt.setInt(4, relatorioBemEstar.getQuantidadePausas());
        stmt.setInt(5, relatorioBemEstar.getNivelEstresse());
        stmt.setString(6, relatorioBemEstar.getDescricao());
        stmt.setInt(7, relatorioBemEstar.getUsuario().getId());

        stmt.execute();

        stmt.close();

        return novoId;
    }

    public void atualizarRelatorioBemEstar(Connection conexao, RelatorioBemEstar relatorioBemEstar) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Relatorio_Bem_Estar set pt_energia = ?, qtd_pausas = ?, pt_estresse = ?, ds_relatorio_bem_estar = ? where id_relatorio_bem_estar = ?");

        stmt.setInt(1, relatorioBemEstar.getNivelEnergia());
        stmt.setInt(2, relatorioBemEstar.getQuantidadePausas());
        stmt.setInt(3, relatorioBemEstar.getNivelEstresse());
        stmt.setString(4, relatorioBemEstar.getDescricao());
        stmt.setInt(5, relatorioBemEstar.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }

    public void deletarRelatorioBemEstar(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Delete from Relatorio_Bem_Estar where id_relatorio_bem_estar = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }
}
