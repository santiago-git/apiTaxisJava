package webservices;

import dao.OperAsignacionIm;
import dao.OperCarroIm;
import dao.OperReservaIm;
import dto.Asignacion;
import dto.Carro;
import dto.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("reserva")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
@Produces({MediaType.APPLICATION_JSON})
public class OperacionesReserva {

    @POST
    @Path("reservartaxi")
    public Response reservarTaxi(Reserva r) {

        OperAsignacionIm operA = new OperAsignacionIm();
        
        int res1 = operA.cambiarOcupado(r.getIdAsignacion());

        if (res1 == 1) {
            OperReservaIm operR = new OperReservaIm();
            int res2 = operR.crearCarrera(r);

            if (res2 == 1) {
                return Response.status(200).entity(r).build();
            }

        }

        return Response.status(500).entity("No se pudo reservar el taxi").build();
    }

    @GET
    @Path("listar")
    public List<Reserva> listar() {
        OperReservaIm operR = new OperReservaIm();

        List<Reserva> lR = operR.listarReservas();

        if (lR != null) {
            return lR;
        }
        return null;
    }
    
    @GET
    @Path("{idReserva}")
    public Reserva consultarDisponibles(@PathParam("idReserva") Integer idReserva) {

        OperReservaIm op = new OperReservaIm();
        Reserva r = op.consultar(idReserva);

        if (r != null) {
            return r;
        }
        return null;
    }

}
