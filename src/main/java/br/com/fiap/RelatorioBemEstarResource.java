package br.com.fiap;

import br.com.fiap.beans.RelatorioBemEstar;
import br.com.fiap.bo.RelatorioBemEstarBO;
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

@Path("/relatorio_bem_estar")
public class RelatorioBemEstarResource {
    private final RelatorioBemEstarBO relatorioBemEstarBO = new RelatorioBemEstarBO();
    public RelatorioBemEstarResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRelatoriosBemEstarRs() {
        try {
            ArrayList<RelatorioBemEstar> listaRelatorios =  relatorioBemEstarBO.selecionarTodosBO();
            return Response.ok(listaRelatorios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRelatoriosBemEstarPorIdUsuarioRs(@PathParam("id") int id) {
        try {
            ArrayList<RelatorioBemEstar> listaRelatorios =  relatorioBemEstarBO.selecionarTodosPorIdUsuarioBO(id);
            return Response.ok(listaRelatorios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarRelatorioBemEstar(RelatorioBemEstar relatorioBemEstar, @Context UriInfo uriInfo) {
        try {
            int novo_id_relatorio = relatorioBemEstarBO.criarRelatorioBemEstarBO(relatorioBemEstar);
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
    public Response atualizarRelatorioBemEstarRs(RelatorioBemEstar relatorioBemEstar, @Context UriInfo uriInfo){
        try {
            relatorioBemEstarBO.atualizarRelatorioBemEstarBO(relatorioBemEstar);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRelatorioBemEstarRs(@PathParam("id") int id){
        try {
            relatorioBemEstarBO.deletarRelatorioBemEstarBO(id);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
