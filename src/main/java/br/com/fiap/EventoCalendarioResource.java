package br.com.fiap;

import br.com.fiap.beans.EventoCalendario;
import br.com.fiap.bo.EventoCalendarioBO;
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

@Path("/evento_calendario")
public class EventoCalendarioResource {
    private final EventoCalendarioBO eventoCalendarioBO = new EventoCalendarioBO();

    public EventoCalendarioResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarEventosCalendarioRs() {
        try {
            ArrayList<EventoCalendario> listaEventos =  eventoCalendarioBO.selecionarTodosBO();
            return Response.ok(listaEventos).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarEventosCalendarioPorIdUsuarioRs(@PathParam("id") int id) {
        try {
            ArrayList<EventoCalendario> listaEventos =  eventoCalendarioBO.selecionarTodosPorIdUsuario(id);
            return Response.ok(listaEventos).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarEventoCalendario(EventoCalendario eventoCalendario, @Context UriInfo uriInfo) {
        try {
            int novo_id_evento_calendario = eventoCalendarioBO.criarEventoCalendarioBO(eventoCalendario);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_evento_calendario)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEventoCalendarioRs(EventoCalendario eventoCalendario, @Context UriInfo uriInfo){
        try {
            eventoCalendarioBO.atualizarEventoCalendarioBO(eventoCalendario);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarEventoCalendarioRs(@PathParam("id") int id){
        try {
            eventoCalendarioBO.deletarEventoCalendarioBO(id);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}
