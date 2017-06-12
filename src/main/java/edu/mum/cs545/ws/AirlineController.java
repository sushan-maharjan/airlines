package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;


@Named
@Path("airline")
public class AirlineController {

	@Inject
	AirlineService airlineService;

	@GET
	public List<Airline> getAllAirlines(){
		return airlineService.findAll();
	}

	@GET
	@Path("byName/{name}")
	public Airline getAirlineByName(@PathParam("name") String name){
		return airlineService.findByName(name);
	}

	@POST
	@Path("new")
	@Consumes("application/json")
	@Produces("application/json")
	public Airline createAirline(Airline airline){
		airlineService.create(airline);
		return airlineService.find(airline);
	}

	@DELETE
	@Path("delete/{id}")
	public Response deleteAirline(@PathParam("id") long id){
		try{
			Airline airline = new Airline();
			airline.setId(id);
			airlineService.delete(airlineService.find(airline));
			return Response.ok().entity(airlineService.find(airline)).build();
		}
		catch (Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("deleteByName/{name}")
	public void deleteAirlineByName(@PathParam("name") String name){
		airlineService.delete(airlineService.findByName(name));
	}

	@PUT
	@Path("update/{id}")
	@Consumes("application/json")
	public Response update(Airline airline, @PathParam("id") long id) {
		try {
			airline.setId(id);
			airlineService.update(airline);
			return Response.ok().entity(airlineService.find(airline)).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
}
