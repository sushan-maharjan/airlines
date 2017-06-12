package cs545.airline.controllers;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by trauma_sushan on 6/11/2017.
 */
@Named("flightController")
@RequestScoped
public class FlightController {
    @Inject
    FlightService flightService;

    @Inject
    private AirlineService airlineService;
    @Inject
    private AirportService airportService;

    private List<Flight> flightList;

    public void displayFlights(String filterOptions, String filterText){
        if("1".equals(filterOptions)){
            // Instantiate a Date object
            DateFormat format = new SimpleDateFormat("MM/dd/yy hh:mm", Locale.ENGLISH);
            Date date = null;
            try {
                date = format.parse(filterText);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date);

            flightList = flightService.findByArrival(date);
        }
        else if("2".equals(filterOptions)){
            Airline airline = airlineService.findByName(filterText);
            flightList = flightService.findByAirline(airline);
        }
        else if("3".equals(filterOptions)){
            // Instantiate a Date object
            DateFormat format = new SimpleDateFormat("MM/dd/yy hh:mm", Locale.ENGLISH);
            Date date = null;
            try {
                date = format.parse(filterText);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(date);

            flightList = flightService.findByDeparture(date);
        }
        else if("4".equals(filterOptions)){
            List<Airport> airports = airportService.findByCity(filterText);
            for(Airport airport : airports){
                flightList = flightService.findByDestination(airport);
            }
        }
    }

    public List<Flight> getAllFlights() {
        if(flightList != null){
            return flightList;
        }else{
            return flightService.findAll();
        }
    }


}
