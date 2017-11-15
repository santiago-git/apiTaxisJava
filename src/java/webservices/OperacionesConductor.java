package webservices;

import dao.OperConductorIm;
import dto.Conductor;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("conductor")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
@Produces({MediaType.APPLICATION_JSON})
public class OperacionesConductor {

    @POST
    @Path("login")
    public Response login(Conductor c) {
        OperConductorIm operC = new OperConductorIm();
        Conductor cond = operC.login(c);

        if (cond != null) {
            return Response.status(200).entity(cond).build();
        }
        return Response.status(405).entity("No existe un conductor con la cedula especificada").build();

    }

}
