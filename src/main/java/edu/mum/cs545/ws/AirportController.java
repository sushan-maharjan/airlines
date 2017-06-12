package edu.mum.cs545.ws;

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;

import javax.inject.Inject;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by trauma_sushan on 6/12/2017.
 */

    @Named
    @Path("airport")
    public class AirportController {
        @Inject
        private AirportService airportService;

        @GET
        public List<Airport> getAllAirport(){
            List<Airport> listOfAirports = airportService.findAll();
            return listOfAirports;
        }

        @POST
        @Path("new")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response createAirport(Airport airport) {
            try {
                airportService.create(airport);
                return Response.ok().entity(airport).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(500).build();
            }
        }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Path("update/{id}")
        public Response updateAirport(Airport airport, @PathParam("id") long id) {
            try {
                airport.setId(id);
                airportService.update(airport);
                return Response.ok().entity(airport).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(500).build();
            }
        }

        @DELETE
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Path("delete/{id}")
        public Response deleteAirport(@PathParam("id") long id){
            try{
                Airport airport =new Airport();
                airport.setId(id);
                airport = airportService.find(airport);
                airportService.delete(airport);
                return Response.ok().entity(airport).build();
            }catch (Exception e){
                e.printStackTrace();
                return Response.status(500).build();
            }
        }
}
