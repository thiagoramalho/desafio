package br.com.coopersystem.restclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/odata")
@RegisterRestClient
public interface CotacaoService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("CotacaoDolarDia(dataCotacao=@dataCotacao)")
	public Response cotacaoDolarDia(@QueryParam("@dataCotacao") String dataCotacao);

}
