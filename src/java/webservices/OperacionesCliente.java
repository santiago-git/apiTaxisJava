package webservices;

import dao.OperAsignacionIm;
import dao.OperCarroIm;
import dto.Asignacion;
import dto.Carro;
import dto.Conductor;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import confRespuesta.Respuesta;
import dao.OperClienteIm;
import dto.Cliente;

@Path("cliente")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
@Produces({MediaType.APPLICATION_JSON})
public class OperacionesCliente {

    @POST
    @Path("validarcedula")
    public Response validarCedula(Cliente c) {
        OperClienteIm operC = new OperClienteIm();
        Cliente cli = operC.validarCliente(c);

        if (cli != null) {
            return Response.status(200).entity(cli).build();
        }
        return Response.status(500).entity("Error").build();
    }

}
