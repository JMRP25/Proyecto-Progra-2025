/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.model.Vendedores;
import com.mycompany.repositorio.VendedoresRepositorio;
import io.vertx.core.json.JsonObject;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Path("/Vendedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class vendedoresController {

    @Inject
    VendedoresRepositorio vendedoresRe;

    @POST
    public Response saveVendedores(Vendedores vendedor) {
        JsonObject JsonResponse = new JsonObject();
        try {
            vendedoresRe.create(vendedor);
            System.out.println("nuevo " + vendedor.toString());
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Vendedor creado!!");

            return Response.ok().entity(JsonResponse).build();

        } catch (Exception e) {
            vendedoresRe.create(vendedor);
            System.out.println("nuevo " + vendedor.toString());
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Vendedor NO creado!!");

            return Response.serverError().entity(JsonResponse).build();

        }
    }

    @GET
    public Response findVendedores() {
        JsonObject JsonResponse = new JsonObject();
        try {
            List<Vendedores> lstVendedores = vendedoresRe.findVendedores();
            ObjectMapper object = new ObjectMapper();
            String jsonA = object.writeValueAsString(lstVendedores);
            JSONParser parser = new JSONParser();
            JSONArray jsonC = (JSONArray) parser.parse(jsonA);

            JsonResponse.put("estado", 1);
            JsonResponse.put("Mensaje", "Consulta Exitosa!!");
            JsonResponse.put("Vendedores", jsonC);

            return Response.ok().entity(JsonResponse).build();

        } catch (Exception e) {
            JsonResponse.put("estado", 0);
            JsonResponse.put("Mensaje", "Consulta NO Exitosa!!");
            return Response.serverError().build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response updateVendedores(@PathParam("id") Long id, Vendedores vendedorActualizado) {
        JSONObject JsonResponse = new JSONObject();
        try {
            Vendedores vendedorExistente = vendedoresRe.findVendedores_id(id);
            if (vendedorExistente == null) {
                JsonResponse.put("Estado", 0);
                JsonResponse.put("Mensaje", "Vendedor no encontrado!!");
                return Response.status(Response.Status.NOT_FOUND).entity(JsonResponse).build();
            }

            // Actualizando los campos
            vendedorExistente.setNombre(vendedorActualizado.getNombre());
            vendedorExistente.setCorreo(vendedorActualizado.getCorreo());
           

            vendedoresRe.updateVendedores(vendedorExistente);

            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Vendedor actualizado!!");
            return Response.ok().entity(JsonResponse).build();

        } catch (Exception e) {
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Error al actualizar el Vendedor!!");
            return Response.serverError().build();
        }
    }

}
