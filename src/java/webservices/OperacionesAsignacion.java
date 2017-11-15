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

@Path("asignacion")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
@Produces({MediaType.APPLICATION_JSON})
public class OperacionesAsignacion {

    @GET
    @Path("listar")
    public List<Asignacion> seleccionarAsignaciones() {
        OperAsignacionIm op = new OperAsignacionIm();
        List<Asignacion> l = new ArrayList<>();
        l = op.consultar();
        return l;
    }

    @POST
    @Path("asignarCarroConductor")
    public Response asignarCarroConductor(Asignacion a) {
        //System.out.println(a.getIdCarro().getAsignado() + " " + a.getIdCarro().getPlaca());
        Carro c = new Carro();
        c.setAsignado(false);//en la operacion le asigna true
        c.setPlaca(a.getIdCarro().getPlaca());

        OperCarroIm opCarro = new OperCarroIm();//cambia estado de carro
        int resC = opCarro.cambiarEstado(c);
        if (resC == 1) {
            OperAsignacionIm op = new OperAsignacionIm();//asigna usuario con carro
            op.asignarCarroConductor(a);
            return Response.status(200).entity("ok").build();
        }
        return Response.status(500).entity("Error").build();
    }

    @GET
    @Path("usuario/{cc}")
    public Response consultarUsuarioAsignado(@PathParam("cc") Long cc) {

        OperAsignacionIm op = new OperAsignacionIm();
        Asignacion a = op.consultarUsuarioAsignado(cc);

        if (a != null) {
            return Response.status(200).entity(a).build();
        }
        return Response.status(405).entity("El usuario no tiene vehiculos asignados").build();
    }

    @POST
    @Path("terminarturno")
    public Response terminarTurno(Asignacion a) {

        OperAsignacionIm opAsignacion = new OperAsignacionIm();
        int resA = opAsignacion.cambiarEstado(a);
        if (resA == 1) {
            OperCarroIm opCarro = new OperCarroIm();
            int resC = opCarro.cambiarEstado(a.getIdCarro());//cambia estado de carro

            if (resC == 1) {
                return Response.status(200).entity("ok").build();
            }

        }
        return Response.status(500).entity("Error").build();
    }

    @POST
    @Path("terminarcarrera")
    public Response terminarCarrera(Asignacion a) {

        OperAsignacionIm opAsignacion = new OperAsignacionIm();
        int resA = opAsignacion.cambiarOcupado(a);
        if (resA == 1) {
            return Response.status(200).entity("ok").build();
        }
        return Response.status(500).entity("Error").build();
    }

    @GET
    @Path("consultardisponibles")
    public List<Asignacion> consultarDisponibles() {

        OperAsignacionIm op = new OperAsignacionIm();
        List<Asignacion> lista = op.consultarDisponibles();

        if (lista != null) {
            return lista;
        }
        return null;
    }
    
    @GET
    @Path("{idAsig}")
    public Asignacion consultarDisponibles(@PathParam("idAsig") Integer idAsig) {

        OperAsignacionIm op = new OperAsignacionIm();
        Asignacion a = op.consultar(idAsig);

        if (a != null) {
            return a;
        }
        return null;
    }

}
