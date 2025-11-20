package br.com.fiap;

import br.com.fiap.beans.RotinaSemanal;
import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.RotinaSemanalBO;
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

@Path("/rotina_semanal")
public class RotinaSemanalResource {
    private final RotinaSemanalBO rotinaSemanalBO = new RotinaSemanalBO();

    public RotinaSemanalResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRotinasSemanaisRs() {
        try {
            ArrayList<RotinaSemanal> listaRotinas =  rotinaSemanalBO.selecionarTodosBO();
            return Response.ok(listaRotinas).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/id_usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarRotinaSemanalPorIdUsuarioRs(@PathParam("id") int id) {
        try {
            RotinaSemanal rotinaSemanal = rotinaSemanalBO.selecionarRotinaSemanalPorIdUsuario(id);
            if (rotinaSemanal == null){
                return Response.ok().status(Response.Status.NOT_FOUND).build();
            }
            else {
                return Response.ok(rotinaSemanal).build();
            }
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarRotinaSemanal(RotinaSemanal rotinaSemanal, @Context UriInfo uriInfo) {
        try {
            int novo_id_rotina = rotinaSemanalBO.criarRotinaSemanalBO(rotinaSemanal);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_rotina)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarTurnosRotinaSemanalRs(RotinaSemanal rotinaSemanal, @Context UriInfo uriInfo){
        try {
            boolean sucesso = rotinaSemanalBO.atualizarTurnosRotinaSemanalBO(rotinaSemanal);
            if (sucesso){
                return Response.ok().build();
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/anotacao")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarAnotacaoRotinaSemanalRs(RotinaSemanal rotinaSemanal, @Context UriInfo uriInfo){
        try {
            boolean sucesso = rotinaSemanalBO.atualizarAnotacaoRotinaSemanal(rotinaSemanal);
            if (sucesso){
                return Response.ok().build();
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("{id}/{dia}/{turno}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDiaRotinaSemanalRs(@PathParam("id") int id, @PathParam("dia") String dia, @PathParam("turno") String turno){
        try {
            rotinaSemanalBO.atualizarDiaRotinaSemanal(id, dia, turno);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRotinaSemanalRs(@PathParam("id") int id){
        try {
            rotinaSemanalBO.deletarRotinaSemanalBO(id);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
