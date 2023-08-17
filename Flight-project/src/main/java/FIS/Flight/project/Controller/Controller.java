package FIS.Flight.project.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import FIS.Flight.project.Entites.City;
import FIS.Flight.project.Entites.Flight;
import FIS.Flight.project.Entites.FlightHistory;
import FIS.Flight.project.Entites.FlightKey;
import FIS.Flight.project.Entites.ScheduledFlight;
import FIS.Flight.project.Rest.Cityrepo;
import FIS.Flight.project.Rest.Flightrepo;
import FIS.Flight.project.Rest.Historyrepo;
import FIS.Flight.project.Rest.Scheduledrepo;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
public class Controller {

	@Autowired
	private Cityrepo cityrepo;
	@Autowired
	private Flightrepo flightrepo;
	@Autowired
	private Historyrepo historyrepo;
	@Autowired
	private Scheduledrepo scheduledrepo;

	// 1
	@GetMapping("/Getcities")
	@Operation(summary = "Get all cities")
	public List<String> getCities() {
		return  cityrepo.citynames();
	}

	// 2
	@GetMapping("/Flights/pagination/{num}")
	@Operation(summary = "Get flights details by given page")

	public List<Flight> getFlightsByPage(@PathVariable("num") int num) {
		var result = flightrepo.findAll(PageRequest.of(num, 5));
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " flight details Not Found");

		} else {
			return result.getContent();
		}
	}

	// 3
	@GetMapping("/flights/{from}/{To}")
	@Operation(summary = "List  of flights from given from_city to to_city")

	public List<Flight> getlist(@PathVariable("from") String from, @PathVariable("To") String to) {
		return flightrepo.gettravellist(from, to);
	}

	// 4
	@GetMapping("/flightHistory/{flightno}")
	@Operation(summary = "List flight history by flightno")

	public List<FlightHistory> getdetails(@PathVariable("flightno") String no) {
		return flightrepo.findById(no).get().getFlightno_fh();

	}

	// 5
	@PostMapping("/AddScheduledflight")
	@Operation(summary = "Add Scheduled flight")

	public ScheduledFlight addSch(@RequestParam("flightno") String no, @RequestParam("depdate") LocalDate depdate,
			@RequestParam("arrdate") LocalDate arrdate) {

		var a = flightrepo.findById(no);
		if (a.isPresent()) {

			var t = flightrepo.findById(no).get();

			FlightKey em = new FlightKey();
			em.setFlightno(no);
			em.setDep_date(depdate);

			ScheduledFlight sf = new ScheduledFlight();
			sf.setArrdate_sf(arrdate);
			sf.setArrtime_sf(t.getArr_time());
			sf.setTocity_sf(t.getTocity_flight());
			sf.setFromcity_sf(t.getFromcity_flight());
			sf.setMins_sf(t.getDur_min());
			sf.setDeptime_sf(t.getDep_time());
			sf.setKey_sf(em);
			scheduledrepo.save(sf);

			return sf;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " flight number Not Found");

		}

	}

	// 6
	@PostMapping("/Addflight")
	@Operation(summary = "Add flights")

	public Flight addflight(@RequestParam("flightno") String fno, @RequestParam("fromcity") String from,
			@RequestParam("tocity") String to, @RequestParam("min") int min, @RequestParam("deptime") LocalTime deptime,
			@RequestParam("arrivalTime") LocalTime arrivalTime, @RequestParam("aircraft") String aircraft) {

		City fromcity =  cityrepo.findById(from).get();
		City tocity =  cityrepo.findById(to).get();

		Flight f = new Flight();
		f.setNo(fno);
		f.setFromcity_flight(fromcity);
		f.setTocity_flight(tocity);
		f.setDur_min(min);
		f.setDep_time(deptime);
		f.setArr_time(arrivalTime);
		f.setAircraft(aircraft);

		flightrepo.save(f);

		return f;
	}

	// 7
	@DeleteMapping("/DelSch/{date1}/{date2}")
	@Operation(summary = "Delete the scheduled flights with the range of given dates")

	public int cancelsf(@PathVariable("date1") LocalDate date1, @PathVariable("date2") LocalDate date2) {
		return scheduledrepo.scheduledflightcancel(date1, date2);
	}

	// 8
	@GetMapping("/Delayed/{time}")
	@Operation(summary = "Get flights delayed  by given minutes")
	public List<FlightHistory> getlist(@PathVariable("time") int time) {
		return historyrepo.delayedFlights(time);
	}

	// 9
	@PostMapping("/Addcity")
	@Operation(summary = "Add row to the Cities table")

	public String addcity(@RequestBody City city) {
		if ( cityrepo.findById(city.getCode()).isEmpty()) {
			 cityrepo.save(city);
			return "City added successfully";
		} else {
			return "City already exists";
		}

	}

	@DeleteMapping("/Delcities/{code}")
	@Operation(summary = "Deleting Cities by given code ")

	public String deleting(@PathVariable("code") String code) {
		var y = cityrepo.findById(code);
		if (y.isPresent()) {
			 cityrepo.deleteById(code);
			return "deleted Succesfully";
		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " City Code Not Found");

		}
	}

	@PutMapping("/updatecity/{code}/{utc}")
	@Operation(summary = "Update City utc by given code")

	public City updating(@PathVariable("code") String code, @PathVariable("utc") int utc) {
		var c =  cityrepo.findById(code);
		if (c.isPresent()) {
			var t = c.get();
			t.setMins(utc);
			 cityrepo.save(t);
			return t;
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " Not Found");
	}

	// 10

	@GetMapping("/Flight/{date}/{city}")
	@Operation(summary = "Get flights for given date from given city ")

	public List<String> getScheduledflight(@PathVariable("date") LocalDate date, @PathVariable("city") String city) {
		return scheduledrepo.scheduledflight(date, city);

	}

	

}
