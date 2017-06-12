package edu.mum.cs545.ws;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.sun.org.apache.regexp.internal.RE;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
public class FlightController {

	@Inject
	FlightService flightService;
	
	@GET
    @Path("")
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
	public Response createFlight(Flight flight){
        try {
            flightService.create(flight);
            return Response.ok().entity(flightService.find(flight)).build();
        }
        catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
	}

	@DELETE
    @Path("delete/{id}")
    public Response deleteFlight(@PathParam("id") long id){
	    try{
            Flight flight = new Flight();
            flight.setId(id);
            flightService.delete(flightService.find(flight));
            return Response.ok().entity(flightService.find(flight)).build();
        }
        catch (Exception e){
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("update/{id}")
    @Consumes("application/json")
    public Response updateFlight(Flight flight, @PathParam("id") long id){
       try{
           flight.setId(id);
           flightService.update(flight);
           return Response.ok().entity(flightService.find(flight)).build();
       }
       catch (Exception e){
           return Response.status(500).entity(e.getMessage()).build();
       }
    }

}
