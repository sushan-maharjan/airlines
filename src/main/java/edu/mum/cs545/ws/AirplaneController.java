package edu.mum.cs545.ws;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

import javax.ws.rs.GET;

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
	
}
