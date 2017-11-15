package webservices;

import dao.OperCarroIm;
import dto.Carro;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("carro")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
@Produces({MediaType.APPLICATION_JSON})
public class OperacionesCarro {

    /* @GET
    @Path("sumar")
    public Response sumar(@QueryParam("a") int a, @QueryParam("b") int b) {
        Respuesta r = new Respuesta();
        r.setCodigo(1);
        r.setDescripcion("Sumo adecuadamente");
        r.setSuma(a + b);

        return Response.status(200).entity(r).build();
    }*/
    @GET
    @Path("listar")
    public List<Carro> seleccionarCarros() {
        OperCarroIm op = new OperCarroIm();
        List<Carro> l = new ArrayList<>();
        l = op.consultar();
        return l;
    }

    @POST
    @Path("crear")
    public Response crearCarro(Carro c) {
        OperCarroIm op = new OperCarroIm();
        op.insertar(c);
        return Response.status(200).entity(c).build();
    }

    @PUT
    @Path("cambiarEstado")
    public Response cambiarEstado(Carro c) {

        OperCarroIm op = new OperCarroIm();
        int res = op.cambiarEstado(c);
        if (res == 1) {
            return Response.status(200).entity(c).build();
        }
        return Response.status(200).entity(null).build();
    }

    @DELETE
    @Path("{placa}")
    @Produces(MediaType.APPLICATION_XML)
    public String eliminarCarro(@PathParam("placa") String placa) {
        OperCarroIm op = new OperCarroIm();
        List<Carro> l = new ArrayList<>();
        op.eliminar(placa);
        return placa;
    }

    @GET
    @Path("listarnoasignados")
    public List<Carro> seleccionarCarrosNoAsignados() {
        OperCarroIm op = new OperCarroIm();
        List<Carro> l = new ArrayList<>();
        l = op.consultarNoAsignados();
        return l;
    }

}
