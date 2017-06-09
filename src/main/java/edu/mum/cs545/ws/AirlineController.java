package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;

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
}
