package resources;

import client.UserRestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import repositories.UserRepository;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    @RestClient
    UserRestClient userRestClient;

    @Inject
    UserRepository userRepository;

    @GET
    @Path("")
    @Transactional
    public Response getClients() {
        List<models.User> users = userRestClient.getUsers();
        List<User> userList =  this.userRepository.saveAll(users);
        return Response.ok(userList).build();
    }
    @GET
    @Path("/persisted")
    @Transactional
    public Response getUsersPersisted() {
        List<User> userList =  this.userRepository.findAll().list();
        return Response.ok(userList).build();
    }

}
