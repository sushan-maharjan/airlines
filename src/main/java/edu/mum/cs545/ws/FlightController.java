package edu.mum.cs545.ws;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

import javax.ws.rs.GET;

@Named
@Path("flight")
public class FlightController {

	@Inject
	FlightService flightService;
	
	@GET
	public List<Flight> getAllFlights(){
		return flightService.findAll();
	}
}
