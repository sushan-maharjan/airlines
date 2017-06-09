package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;

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
	public List<Airline> deleteAirline(@PathParam("id") long id){
		Airline airline = new Airline();
		airline.setId(id);
		airlineService.delete(airlineService.find(airline));
		return airlineService.findAll();
	}

	@DELETE
	@Path("deleteByName/{name}")
	public void deleteAirlineByName(@PathParam("name") String name){
		airlineService.delete(airlineService.findByName(name));
	}

	@PUT
	@Path("update/{id}")
	@Consumes("application/json")
	public Airline update(@PathParam("id") long id){
		Airline airline = new Airline();
		airline.setId(id);
		airlineService.update(airlineService.find(airline));
		return airlineService.find(airline);
	}
}
