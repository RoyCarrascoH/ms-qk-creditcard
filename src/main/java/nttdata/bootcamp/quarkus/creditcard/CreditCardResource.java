package nttdata.bootcamp.quarkus.creditcard;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.creditcard.entity.CreditCardEntity;
import nttdata.bootcamp.quarkus.creditcard.service.CreditCardService;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/creditcard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreditCardResource {
    private static final Logger LOGGER = Logger.getLogger(CreditCardResource.class.getName());
    @Inject
    private CreditCardService creditCardService;
    @GET
    public List<CreditCardEntity> getClients() {
        return creditCardService.listAll();
    }
    @GET
    @Path("{idCreditCard}")
    public CreditCardEntity viewClientDetails(@PathParam("idCreditCard") Long idCreditCard) {
        CreditCardEntity entity = creditCardService.findById(idCreditCard);
        if (entity == null) {
            throw new WebApplicationException("Credit Card with id of " + idCreditCard + " does not exist.", 404);
        }
        return entity;
    }
    @POST
    @Transactional
    public Response create(CreditCardEntity creditCardEntity) {
        creditCardService.save(creditCardEntity);
        return Response.ok(creditCardEntity).status(201).build();
    }
    @PUT
    @Path("{idCreditCard}")
    @Transactional
    public CreditCardEntity updateClient(@PathParam("idClient") Long idClient, CreditCardEntity creditCardEntity) {
        if (creditCardEntity.getIdCreditCard() == null) {
            throw new WebApplicationException("Credit Card person type was not set on request.", 422);
        }
        CreditCardEntity entity = creditCardService.findById(idClient);
        if (entity == null) {
            throw new WebApplicationException("Client with id of " + idClient + " does not exist.", 404);
        }

        //entity = Utilitarios.saveClient(entity, creditCardEntity);
        return entity;
    }
    @DELETE
    @Path("{idClient}")
    @Transactional
    public Response delete(@PathParam("idClient") Long idClient) {
        CreditCardEntity
                entity = creditCardService.findById(idClient);
        if (entity == null) {
            throw new WebApplicationException("Client with id of " + idClient + " does not exist.", 404);
        }
        creditCardService.delete(entity.getIdCreditCard());
        return Response.status(204).build();
    }
}