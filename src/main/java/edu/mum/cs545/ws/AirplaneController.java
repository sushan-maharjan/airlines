package edu.mum.cs545.ws;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;

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
	public Airplane createAirplane(Airplane airplane){
		airplaneService.create(airplane);
		return airplaneService.find(airplane);
	}

	@DELETE
	@Path("delete/{id}")
	public void deleteAirplane(@PathParam("id") long id){
		Airplane airplane = new Airplane();
		airplane.setId(id);
		airplaneService.delete(airplaneService.find(airplane));
	}
	
}
