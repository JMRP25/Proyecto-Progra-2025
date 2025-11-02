/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/prueba")
public class ControladorPrueba {

    @GET
    public Response prueba() {
        return Response.ok("Endpoint de prueba está funcionando").build();
    }
}
