package br.com.fiap;

import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.UsuarioBO;
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

@Path("/usuario")
public class UsuarioResource {
    private final UsuarioBO usuarioBO = new UsuarioBO();

    public UsuarioResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarUsuariosRs() {
        try {
            ArrayList<Usuario> listaUsuarios =  usuarioBO.selecionarTodosBO();
            return Response.ok(listaUsuarios).build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarUsuarioPorEmailRs(@PathParam("email") String email) {
        try {
            Usuario usuario = usuarioBO.selecionarUsuarioPorEmailBO(email);
            if (usuario == null){
                return Response.ok().status(Response.Status.NOT_FOUND).build();
            }
            else {
                return Response.ok(usuario).build();
            }
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response selecionarUsuarioPorIdRs(@PathParam("id") int id) {
        try {
            Usuario usuario = usuarioBO.selecionarUsuarioPorIdBO(id);
            if (usuario == null){
                return Response.ok().status(Response.Status.NOT_FOUND).build();
            }
            else {
                return Response.ok(usuario).build();
            }
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastraUsuarioRs(Usuario usuario, @Context UriInfo uriInfo) {
        try {
            int novo_id_paciente = usuarioBO.cadastrarUsuarioBO(usuario);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(novo_id_paciente)
                    .build();
        }
        catch (Exception e) {
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuarioRs(Usuario usuario, @Context UriInfo uriInfo){
        try {
            usuarioBO.atualizarUsuarioBO(usuario);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/email/{id_usuario}/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEmailUsuarioRs(@PathParam("id_usuario") int id_usuario, @PathParam("email") String email){
        try {
            boolean atualizou = usuarioBO.atualizarEmailUsuarioBO(id_usuario, email);
            if (atualizou){
                return Response
                        .status(Response.Status.OK)
                        .build();
            }
            else {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .build();
            }
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/senha/{id_usuario}/{senha}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarSenhaUsuarioRs(@PathParam("id_usuario") int id_usuario, @PathParam("senha") String senha){
        try {
            boolean atualizou = usuarioBO.atualizarSenhaUsuarioBO(id_usuario, senha);
            if (atualizou){
                return Response
                        .status(Response.Status.OK)
                        .build();
            }
            else {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .build();
            }
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @PUT
    @Path("/preferencia/{id_usuario}/{preferencia}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPreferenciaUsuarioRs(@PathParam("id_usuario") int id_usuario, @PathParam("preferencia") String preferencia){
        try {
            boolean atualizou = usuarioBO.atualizarPreferenciaUsuarioBO(id_usuario, preferencia);
            if (atualizou){
                return Response
                        .status(Response.Status.OK)
                        .build();
            }
            else {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .build();
            }
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarUsuarioRs(@PathParam("id") int id){
        try {
            usuarioBO.deletarUsuarioBO(id);
            return Response.ok().build();
        }
        catch (Exception e){
            return RequestsExcecoes.ExcecoesConexao(e);
        }
    }
}