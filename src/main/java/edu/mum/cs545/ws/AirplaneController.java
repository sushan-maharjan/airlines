package edu.mum.cs545.ws;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.sun.org.apache.regexp.internal.RE;
import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

@Named
@Path("airplane")
public class AirplaneController {

	@Inject
	AirplaneService airplaneService;
	
	@GET
	public List<Airplane> getAllAirplanes(){
		return airplaneService.findAll();
	}

	@GET
	@Path("byModel/{model}")
	public List<Airplane> getAirplaneByModel(@PathParam("model") String model){
		return airplaneService.findByModel(model);
	}

	@POST
	@Path("new")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createAirplane(Airplane airplane){
		try {
			airplaneService.create(airplane);
			return Response.ok().entity(airplaneService.find(airplane)).build();
		}
		catch (Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("delete/{id}")
	public Response deleteAirplane(@PathParam("id") long id){
		try{

			Airplane airplane = new Airplane();
			airplane.setId(id);
			airplaneService.delete(airplaneService.find(airplane));
			return Response.ok().entity(airplaneService.find(airplane)).build();
		}
		catch (Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("update/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateAirplane(Airplane airplane, @PathParam("id") long id){
		try{
			airplane.setId(id);
			airplaneService.update(airplane);
			return Response.ok().entity(airplaneService.find(airplane)).build();
		}
		catch (Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}

	}
	
}
