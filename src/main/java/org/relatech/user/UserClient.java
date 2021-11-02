package org.relatech.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.relatech.book.BookControllerInterface;

//Un client REST che serve a fare chiamate REST all'esterno
@Path("/users")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserClient extends BookControllerInterface {
	
	@GET
	@Path("/getUser")
	Response getUser(@QueryParam("fiscalCode") String fiscalCode);

}
