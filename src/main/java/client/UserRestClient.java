package client;

import entities.User;
import jakarta.ejb.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/public")
@RegisterRestClient(configKey="novatech-test")
public interface UserRestClient {

    @GET
    @Path("/v2/users")
    List<models.User> getUsers();

}
