package lt.vu.psk.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Forest;
import lt.vu.psk.persistance.ForestDAO;
import lt.vu.psk.rest.contracts.ForestDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/forest")
public class ForestController {

    @Inject
    @Setter @Getter
    private ForestDAO forestDAO;

    @Getter @Setter
    private Forest forest;

    // http://localhost:8080/java-ee-practice/api/forest/1
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id){
        Forest forest = forestDAO.findOne(id);

        if (forest == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ForestDto forestDto = new ForestDto();
        forestDto.setForestName(forest.getForest_name());
        forestDto.setForestRangerId(forest.getForest_ranger());

        return Response.ok(forestDto).build();
    }

    // http://localhost:8080/java-ee-practice/api/forest/1
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer forestId, ForestDto forestDto){
        try {
            Forest existingForest = forestDAO.findOne(forestId);
            if (existingForest == null){
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingForest.setForest_name(forestDto.getForestName());
            existingForest.setForest_ranger(forestDto.getForestRangerId());
            forestDAO.update(existingForest);
            return Response.ok().build();
        } catch (OptimisticLockException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
