package lt.vu.psk.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Tree;
import lt.vu.psk.persistance.ForestDAO;
import lt.vu.psk.persistance.TreeDAO;
import lt.vu.psk.rest.contracts.TreeDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@ApplicationScoped
@Path("/tree")
public class TreeController {

    @Inject
    @Setter @Getter
    private TreeDAO treeDAO;

    @Inject
    @Setter @Getter
    private ForestDAO forestDAO;

    @Inject
    private EntityManager em;

    //http://localhost:8080/java-ee-practice/api/tree/1
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {

        Tree tree = treeDAO.findOne(id);

        if (tree == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        TreeDto treeDto = new TreeDto();
        treeDto.setTreeName(tree.getTree_name());
        treeDto.setForestId(tree.getForest().getId());


        return Response.ok(treeDto).build();
    }

    // http://localhost:8080/PSK-TP-1-1.0-SNAPSHOT/api/trees
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(TreeDto treeDto) {

        try {
            if (treeDto == null || forestDAO.findOne(treeDto.getForestId()) == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Tree treeToCreate = new Tree();
            treeToCreate.setTree_name(treeDto.getTreeName());
            treeToCreate.setForest(forestDAO.findOne(treeDto.getForestId()));
            treeDAO.persist(treeToCreate);

            URI location = UriBuilder.fromResource(TreeController.class)
                    .path("/{id}")
                    .resolveTemplate("id", treeToCreate.getId())
                    .build();

            return Response.created(location).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }

}
