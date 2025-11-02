package com.mycompany.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.model.Clientes;
import com.mycompany.repositorio.ClienteRepositorio;
import org.json.simple.parser.JSONParser;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/clientes")  // ⚠️ CAMBIO 1: Minúscula para seguir convenciones REST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class clienteController {
    
    @Inject
    ClienteRepositorio ClienteR;
    
    // ============= CREAR CLIENTE =============
    @POST
    public Response SaveCliente(Clientes cliente) {
        JSONObject JsonResponse = new JSONObject();
        try {
            ClienteR.create(cliente);
            System.out.println("Nuevo cliente: " + cliente.toString());
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Cliente creado exitosamente");
            return Response.ok().entity(JsonResponse.toString()).build();  // ⚠️ CAMBIO 2: toString()
        } catch (Exception e) {
            e.printStackTrace();  // ⚠️ CAMBIO 3: Ver errores en consola
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Error al crear cliente: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonResponse.toString()).build();
        }
    }
    
    // ============= LISTAR TODOS LOS CLIENTES =============
    @GET
    public Response findClientes() {
        JSONObject JsonResponse = new JSONObject();
        try {
            List<Clientes> lstClientes = ClienteR.findClientes();
            
            // Convertir lista a JSON
            ObjectMapper object = new ObjectMapper();
            String jsonA = object.writeValueAsString(lstClientes);
            JSONParser parser = new JSONParser();
            JSONArray jsonC = (JSONArray) parser.parse(jsonA);
            
            JsonResponse.put("estado", 1);
            JsonResponse.put("Mensaje", "Consulta exitosa");
            JsonResponse.put("Clientes", jsonC);
            
            return Response.ok()
                    .entity(JsonResponse.toString())  // ⚠️ CAMBIO 4: toString()
                    .build();
                    
        } catch (Exception e) {
            e.printStackTrace();  // ⚠️ CAMBIO 5: Ver errores en consola
            JsonResponse.put("estado", 0);
            JsonResponse.put("Mensaje", "Error en consulta: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonResponse.toString()).build();
        }
    }
    
    // ============= BUSCAR CLIENTE POR ID =============
    @GET
    @Path("/{id}")
    public Response findClienteById(@PathParam("id") Long id) {
        JSONObject JsonResponse = new JSONObject();
        try {
            Clientes cliente = ClienteR.findCliente_Id(id);
            
            if (cliente == null) {
                JsonResponse.put("Estado", 0);
                JsonResponse.put("Mensaje", "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(JsonResponse.toString()).build();
            }
            
            // Convertir cliente a JSON
            ObjectMapper object = new ObjectMapper();
            String jsonString = object.writeValueAsString(cliente);
            JSONParser parser = new JSONParser();
            JSONObject jsonCliente = (JSONObject) parser.parse(jsonString);
            
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Cliente encontrado");
            JsonResponse.put("Cliente", jsonCliente);
            
            return Response.ok().entity(JsonResponse.toString()).build();
            
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Error al buscar cliente: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonResponse.toString()).build();
        }
    }
    
    // ============= ACTUALIZAR CLIENTE =============
    @PUT
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") Long id, Clientes clienteActualizado) {
        JSONObject JsonResponse = new JSONObject();
        try {
            Clientes clienteExistente = ClienteR.findCliente_Id(id);
            
            if (clienteExistente == null) {
                JsonResponse.put("Estado", 0);
                JsonResponse.put("Mensaje", "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(JsonResponse.toString()).build();
            }
            
            // Actualizar campos
            if (clienteActualizado.getNombre() != null) {
                clienteExistente.setNombre(clienteActualizado.getNombre());
            }
            if (clienteActualizado.getApellido() != null) {
                clienteExistente.setApellido(clienteActualizado.getApellido());
            }
            if (clienteActualizado.getNit() != null) {
                clienteExistente.setNit(clienteActualizado.getNit());
            }
            if (clienteActualizado.getTelefono() != null) {
                clienteExistente.setTelefono(clienteActualizado.getTelefono());
            }
            if (clienteActualizado.getDireccion() != null) {
                clienteExistente.setDireccion(clienteActualizado.getDireccion());
            }
            
            ClienteR.updateCliente(clienteExistente);
            
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Cliente actualizado exitosamente");
            return Response.ok().entity(JsonResponse.toString()).build();
            
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Error al actualizar cliente: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonResponse.toString()).build();
        }
    }
    
    // ============= ELIMINAR CLIENTE =============
    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Long id) {
        JSONObject JsonResponse = new JSONObject();
        try {
            Clientes cliente = ClienteR.findCliente_Id(id);
            
            if (cliente == null) {
                JsonResponse.put("Estado", 0);
                JsonResponse.put("Mensaje", "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(JsonResponse.toString()).build();
            }
            
            ClienteR.deleteCliente(id);
            
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Cliente eliminado exitosamente");
            return Response.ok().entity(JsonResponse.toString()).build();
            
        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Error al eliminar cliente: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonResponse.toString()).build();
        }
    }
}

