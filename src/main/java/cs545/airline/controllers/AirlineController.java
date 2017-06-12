package cs545.airline.controllers;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by trauma_sushan on 6/11/2017.
 */
@Named("airlineC")
@RequestScoped
public class AirlineController {
    @Inject
    AirlineService airlineService;

    Airline airline;

    AirlineController(){
        airline = new Airline();
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<Airline> getAirlines(){
        return airlineService.findAll();
    }

    public String save(){
        try {
            if (!airline.getName().equals("")) {
                airlineService.create(airline);
            }
        }catch (Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error Saving Data either due to duplicate key or database error"));
        }
        return null;
    }
    public String delete(Airline airline){
        airlineService.delete(airline);
        return null;
    }
    public String update(Airline airline){
        airlineService.update(airline);
        return null;
    }
}
