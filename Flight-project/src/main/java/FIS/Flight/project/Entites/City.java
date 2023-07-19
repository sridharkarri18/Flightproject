package FIS.Flight.project.Entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cities")
public class City {

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "Name")
	private String name;

	@Column(name = "minutes_from_utc")
	private int mins;

	@Column(name = "Country")
	private String country;

	// Mapping between City & Flights

	@OneToMany(mappedBy = "fromcity_flight")
	@JsonIgnore
	private List<Flight> flight_f;

	@OneToMany(mappedBy = "tocity_flight")
	@JsonIgnore
	private List<Flight> flight_t;

	// Mapping between City & FlightHistory

	@OneToMany(mappedBy = "fromcity_fh")
	@JsonIgnore
	private List<FlightHistory> fromflight_fh;
	@OneToMany(mappedBy = "tocity_fh")
	@JsonIgnore
	private List<FlightHistory> toflight_th;

	// Mapping between City & ScheduledFlight

	@OneToMany(mappedBy = "fromcity_sf")
	@JsonIgnore
	private List<ScheduledFlight> flight_fcity;
	@OneToMany(mappedBy = "tocity_sf")
	@JsonIgnore
	private List<ScheduledFlight> flight_tcity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMins() {
		return mins;
	}

	public void setMins(int mins) {
		this.mins = mins;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Flight> getFlight_f() {
		return flight_f;
	}

	public void setFlight_f(List<Flight> flight_f) {
		this.flight_f = flight_f;
	}

	public List<Flight> getFlight_t() {
		return flight_t;
	}

	public void setFlight_t(List<Flight> flight_t) {
		this.flight_t = flight_t;
	}

}