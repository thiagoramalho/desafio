package br.com.coopersystem.restclient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import br.com.coopersystem.util.Cotacao;
import br.com.coopersystem.util.Retorno;
import br.com.coopersystem.util.Util;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@Path("/cotacao")
@Tag(name = "Cotação", description = "Api de cotação")
public class CotacaoResourse {

	private static final Logger LOG = Logger.getLogger(CotacaoResourse.class);

	@Inject
	@RestClient
	CotacaoService cotacaoService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cotacaoDolarDia")
	@Operation(description = "Consulta a cotação do dólar por data", summary = "Consulta a cotação do dólar")
	@APIResponse(responseCode = "400", description = "A requisição não foi aceita. Geralmente por falta de um parâmetro requerido ou incorreto.", content = @Content(schema = @Schema(implementation = Retorno.class)))
	@APIResponse(responseCode = "200", description = "Tudo funcionou como esperado.", content = @Content(schema = @Schema(implementation = Cotacao.class)))
	public Response cotacaoDolarDia(@QueryParam("dataCotacao") String dataCotacao) {

		try {
			String data = dataCotacao.replaceAll("'", "");
			if (!Util.isDayUtil(data)) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new Retorno("Informe um dia útil")).build();
			} else {
				Response response = cotacaoService.cotacaoDolarDia(dataCotacao);

				if (response.getStatus() == Response.Status.OK.getStatusCode()) {
					String json = response.readEntity(String.class);
					JsonObject jsonObject = new JsonObject(json);
					JsonArray jsonArray = jsonObject.getJsonArray("value");
					if (!jsonArray.isEmpty()) {
						return Response.status(Response.Status.OK).entity(jsonArray.getJsonObject(0)).build();
					} else {
						return Response.status(Response.Status.BAD_REQUEST)
								.entity(new Retorno("Não teve cotação. Feriado.")).build();
					}
				}

				return response;

			}
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(new Retorno(e.getMessage())).build();
		}

	}

}
