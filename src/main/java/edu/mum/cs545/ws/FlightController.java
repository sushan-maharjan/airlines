package edu.mum.cs545.ws;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
public class FlightController {

	@Inject
	FlightService flightService;
	
	@GET
	public List<Flight> getAllFlights(){
		return flightService.findAll();
	}

	@GET
	@Path("byFlightNumber/{number}")
	public List<Flight> getFlightByNumber(@PathParam("number") String flightNumber){
		return flightService.findByNumber(flightNumber);
	}

	@GET
	@Path("byAirline/{airline}")
	public List<Flight> getFlightByAirline(@PathParam("airline") Airline airline){
		return flightService.findByAirline(airline);
	}

	@Path("new")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Flight createFlight(Flight flight){
		flightService.create(flight);
		return flightService.find(flight);
	}

	@DELETE
    @Path("delete/{id}")
    public void deleteFlight(@PathParam("id") long id){
	    Flight flight = new Flight();
	    flight.setId(id);
	    flightService.delete(flightService.find(flight));
    }

}
