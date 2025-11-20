package br.com.fiap;

import br.com.fiap.beans.RelatorioProdutividade;
import br.com.fiap.bo.RelatorioProdutividadeBO;
import br.com.fiap.excecoes.RequestsExcecoes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider

@Path("/relatorio_produtividade")
public class RelatorioProdutividadeResource {
    private final RelatorioProdutividadeBO relatorioProdutividadeBO = new RelatorioProdutividadeBO();
    public RelatorioProdutividadeResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRelatoriosProdutividadeRs() {
        try {
            ArrayList<RelatorioProdutividade> listaRelatorios =  relatorioProdutividadeBO.selecionarTodosBO();
            return Response.ok(listaRelatorios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRelatoriosProdutividadePorIdUsuarioRs(@PathParam("id") int id) {
        try {
            ArrayList<RelatorioProdutividade> listaRelatorios =  relatorioProdutividadeBO.selecionarTodosPorIdUsuarioBO(id);
            return Response.ok(listaRelatorios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarRelatorioProdutividade(RelatorioProdutividade relatorioProdutividade, @Context UriInfo uriInfo) {
        try {
            int novo_id_relatorio = relatorioProdutividadeBO.criarRelatorioProdutividadeBO(relatorioProdutividade);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_relatorio)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRelatorioProdutividadeRs(RelatorioProdutividade relatorioProdutividade, @Context UriInfo uriInfo){
        try {
            relatorioProdutividadeBO.atualizarRelatorioProdutividadeBO(relatorioProdutividade);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRelatorioProdutividadeRs(@PathParam("id") int id){
        try {
            relatorioProdutividadeBO.deletarRelatorioProdutividadeBO(id);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
