package FIS.Flight.project.Entites;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {

	@Id
	@Column(name = "flight_no")
	private String No;

	@Column(name = "duration_in_minutes")
	private int dur_min;

	@Column(name = "departure_time")
	private LocalTime dep_time;

	@Column(name = "arrival_time")
	private LocalTime arr_time;

	@Column(name = "aircraft")
	private String aircraft;

	// Mapping between City & Flights

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "from_city", referencedColumnName = "code")
	private City fromcity_flight;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "to_city", referencedColumnName = "code")
	private City tocity_flight;

	// Mapping between flight and ScheduldedFlight

	@OneToMany(mappedBy = "flightno_sf")
	@JsonIgnore
	private List<ScheduledFlight> flight_sf;

	// Mapping between Flights and flight History

	@OneToMany(mappedBy = "flightno_fh")
	@JsonIgnore
	private List<FlightHistory> flightno_fh;

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public int getDur_min() {
		return dur_min;
	}

	public void setDur_min(int dur_min) {
		this.dur_min = dur_min;
	}

	public LocalTime getDep_time() {
		return dep_time;
	}

	public void setDep_time(LocalTime dep_time) {
		this.dep_time = dep_time;
	}

	public LocalTime getArr_time() {
		return arr_time;
	}

	public void setArr_time(LocalTime arr_time) {
		this.arr_time = arr_time;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public List<ScheduledFlight> getFlight_sf() {
		return flight_sf;
	}

	public void setFlight_sf(List<ScheduledFlight> flight_sf) {
		this.flight_sf = flight_sf;
	}

	public List<FlightHistory> getFlightno_fh() {
		return flightno_fh;
	}

	public void setFlightno_fh(List<FlightHistory> flightno_fh) {
		this.flightno_fh = flightno_fh;
	}

	public City getFromcity_flight() {
		return fromcity_flight;
	}

	public void setFromcity_flight(City fromcity_flight) {
		this.fromcity_flight = fromcity_flight;
	}

	public City getTocity_flight() {
		return tocity_flight;
	}

	public void setTocity_flight(City tocity_flight) {
		this.tocity_flight = tocity_flight;
	}

}
