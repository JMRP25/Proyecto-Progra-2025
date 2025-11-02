/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.cliente;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Path("/clientes")
public class ControladorCliente {

    @Inject
    ClienteRepositorio clienteRepo;

    //  injecta 
    private ObjectMapper objectMapper = new ObjectMapper();

 @POST
@Path("/crear")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public Response crearCliente(cliente cliente) {
    Map<String, Object> respuesta = new HashMap<>();
    try {
        // valido si el cliente existe
        if (clienteRepo.findById(cliente.getIdCliente()) != null) {
            respuesta.put("mensaje", "Cliente con este ID ya existe");
            return Response.status(Response.Status.CONFLICT).entity(respuesta).build();
        }

        // creo el cliente
        clienteRepo.create(cliente);
        respuesta.put("mensaje", "Cliente creado exitosamente");
        respuesta.put("cliente", cliente);

        // convierte a json 
        String jsonRespuesta = objectMapper.writeValueAsString(respuesta);
        return Response.status(Response.Status.CREATED).entity(jsonRespuesta).build();
    } catch (Exception e) {
        respuesta.put("mensaje", "Error al crear cliente: " + e.getMessage());
        try {
            String jsonRespuesta = objectMapper.writeValueAsString(respuesta);
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonRespuesta).build();
        } catch (Exception jsonException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la respuesta").build();
        }
    }
}


    // Metodo para obtener un cliente por su ID 
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCliente(@PathParam("id") String id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            cliente cliente = clienteRepo.findById(id);
            if (cliente != null) {
                respuesta.put("mensaje", "Cliente encontrado");
                respuesta.put("cliente", cliente);
            } else {
                respuesta.put("mensaje", "Cliente no encontrado");
            }
            String jsonRespuesta = objectMapper.writeValueAsString(respuesta);
            return Response.ok(jsonRespuesta).build();
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al obtener cliente: " + e.getMessage());
            try {
                String jsonRespuesta = objectMapper.writeValueAsString(respuesta);
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonRespuesta).build();
            } catch (Exception jsonException) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la respuesta").build();
            }
        }
    }

    // Metodo para obtener todos los clientes (GET)
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosClientes() {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            List<cliente> clientes = clienteRepo.findAllClientes();
            respuesta.put("mensaje", "Clientes encontrados");
            respuesta.put("clientes", clientes);
            String jsonRespuesta = objectMapper.writeValueAsString(respuesta);
            return Response.ok(jsonRespuesta).build();
        } catch (Exception e) {
            respuesta.put("mensaje", "Error al obtener clientes: " + e.getMessage());
            try {
                String jsonRespuesta = objectMapper.writeValueAsString(respuesta);
                return Response.status(Response.Status.BAD_REQUEST).entity(jsonRespuesta).build();
            } catch (Exception jsonException) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la respuesta").build();
            }
        }
    }
    
    
     // Método para actualizar cliente
   @PUT
@Path("/actualizar/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)  // Especificar que se devuelve JSON
@Transactional
public Response actualizarCliente(@PathParam("id") String id, cliente clienteActualizado) {
    Map<String, Object> respuesta = new HashMap<>();
    try {
        // Buscar el cliente por su ID
        cliente clienteExistente = clienteRepo.findById(id);
        if (clienteExistente == null) {
            respuesta.put("mensaje", "Cliente no encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
        }

        // Actualizar los campos del cliente
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setNit(clienteActualizado.getNit());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());

        // cambios automaticos con panache
        respuesta.put("mensaje", "Cliente actualizado exitosamente");
        respuesta.put("clienteActualizado", clienteExistente);  
        return Response.ok(respuesta).build();  // json
    } catch (Exception e) {
        respuesta.put("mensaje", "Error al actualizar cliente: " + e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
    }
}
}



