package resources;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class HelloWorldResource {

    @GET
    @Path("/helloWorld")
    public Response getHelloWorld() {
        return Response.ok("Hello World").build();
    }

}
