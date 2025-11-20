package br.com.fiap.dao;

import br.com.fiap.beans.RelatorioProdutividade;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RelatorioProdutividadeDAO {
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    private int gerarNovoId(Connection conexao) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("select MAX(id_relatorio_produtividade) from Relatorio_Produtividade");

        ResultSet rs = stmt.executeQuery();

        int ultimoId = 0;
        while (rs.next()) {
            ultimoId = rs.getInt(1); // pega o id da linha atual
        }

        stmt.close();
        return ultimoId + 1;
    }

    public ArrayList<RelatorioProdutividade> selecionarRelatorios(Connection conexao) throws SQLException {
        ArrayList<RelatorioProdutividade> listaRelatorios = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Relatorio_Produtividade");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            RelatorioProdutividade relatorioProdutividade = new RelatorioProdutividade();
            relatorioProdutividade.setId(rs.getInt(1));
            relatorioProdutividade.setData(rs.getDate(2).toLocalDate());
            relatorioProdutividade.setHorasTrabalhadas(rs.getDouble(3));
            relatorioProdutividade.setQntdDiasPresenciais(rs.getInt(4));
            relatorioProdutividade.setQntdDiasRemotos(rs.getInt(5));
            relatorioProdutividade.setDescricao(rs.getString(6));

            relatorioProdutividade.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(7)));

            listaRelatorios.add(relatorioProdutividade);
        }
        return listaRelatorios;
    }

    public ArrayList<RelatorioProdutividade> selecionarRelatoriosPorIdUsuario(Connection conexao, int id_usuario) throws SQLException {
        ArrayList<RelatorioProdutividade> listaRelatorios = new ArrayList<>();
        PreparedStatement stmt = conexao.prepareStatement("select * from Relatorio_Produtividade where fk_id_usuario = ?");

        stmt.setInt(1, id_usuario);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            RelatorioProdutividade relatorioProdutividade = new RelatorioProdutividade();
            relatorioProdutividade.setId(rs.getInt(1));
            relatorioProdutividade.setData(rs.getDate(2).toLocalDate());
            relatorioProdutividade.setHorasTrabalhadas(rs.getDouble(3));
            relatorioProdutividade.setQntdDiasPresenciais(rs.getInt(4));
            relatorioProdutividade.setQntdDiasRemotos(rs.getInt(5));
            relatorioProdutividade.setDescricao(rs.getString(6));

            relatorioProdutividade.setUsuario(usuarioDAO.selecionarUsuarioPorId(conexao, rs.getInt(7)));

            listaRelatorios.add(relatorioProdutividade);
        }
        return listaRelatorios;
    }

    public int criarRelatorioProdutividade (Connection conexao, RelatorioProdutividade relatorioProdutividade) throws SQLException {
        int novoId = gerarNovoId(conexao);

        PreparedStatement stmt = conexao.prepareStatement("Insert into Relatorio_Produtividade (id_relatorio_produtividade, dt_relatorio_produtividade, qtd_horas_trabalhadas, qtd_dias_presenciais, qtd_dias_remotos, ds_relatorio_produtividade, fk_id_usuario) values (?, ?, ?, ?, ?, ?, ?)");

        LocalDate dataAgora = LocalDate.now();

        stmt.setInt(1, novoId);
        stmt.setDate(2, java.sql.Date.valueOf(dataAgora));
        stmt.setDouble(3, relatorioProdutividade.getHorasTrabalhadas());
        stmt.setInt(4, relatorioProdutividade.getQntdDiasPresenciais());
        stmt.setInt(5, relatorioProdutividade.getQntdDiasRemotos());
        stmt.setString(6, relatorioProdutividade.getDescricao());
        stmt.setInt(7, relatorioProdutividade.getUsuario().getId());

        stmt.execute();

        stmt.close();

        return novoId;
    }

    public void atualizarRelatorioProdutividade(Connection conexao, RelatorioProdutividade relatorioProdutividade) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Update Rotina_Produtividade set qtd_horas_trabalhadas = ?, qtd_dias_presenciais = ?, qtd_dias_remotos = ?, ds_relatorio_produtividade = ? where id_relatorio_produtividade = ?");

        stmt.setDouble(1, relatorioProdutividade.getHorasTrabalhadas());
        stmt.setInt(2, relatorioProdutividade.getQntdDiasPresenciais());
        stmt.setInt(3, relatorioProdutividade.getQntdDiasRemotos());
        stmt.setString(4, relatorioProdutividade.getDescricao());
        stmt.setInt(5, relatorioProdutividade.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }

    public void deletarRelatorioProdutividade(Connection conexao, int id) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement("Delete from Relatorio_Produtividade where id_relatorio_produtividade = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();
        if (linhasAfetadas == 0){
            throw new SQLException("Nenhum registro encontrado.", "02000", 20001);
        }
    }
}
