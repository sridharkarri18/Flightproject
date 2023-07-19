package FIS.Flight.project.Entites;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "scheduled_flights")
public class ScheduledFlight {
	@EmbeddedId
	private FlightKey key_sf;

	@Column(name = "departure_time")
	private LocalTime deptime_sf;

	@Column(name = "arrival_date")
	private LocalDate arrdate_sf;

	@Column(name = "arrival_time")
	private LocalTime arrtime_sf;

	@Column(name = " duration_in_minutes")
	private int mins_sf;

	// Mapping between ScheduldedFlight and flight

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "flight_no", insertable = false, updatable = false)
	private Flight flightno_sf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "from_city", referencedColumnName = "code")
	private City fromcity_sf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "to_city", referencedColumnName = "code")
	private City tocity_sf;

	public FlightKey getKey_sf() {
		return key_sf;
	}

	public void setKey_sf(FlightKey key_sf) {
		this.key_sf = key_sf;
	}

	public LocalTime getDeptime_sf() {
		return deptime_sf;
	}

	public void setDeptime_sf(LocalTime deptime_sf) {
		this.deptime_sf = deptime_sf;
	}

	public LocalDate getArrdate_sf() {
		return arrdate_sf;
	}

	public void setArrdate_sf(LocalDate arrdate_sf) {
		this.arrdate_sf = arrdate_sf;
	}

	public LocalTime getArrtime_sf() {
		return arrtime_sf;
	}

	public void setArrtime_sf(LocalTime arrtime_sf) {
		this.arrtime_sf = arrtime_sf;
	}

	public int getMins_sf() {
		return mins_sf;
	}

	public void setMins_sf(int mins_sf) {
		this.mins_sf = mins_sf;
	}

	public Flight getFlightno_sf() {
		return flightno_sf;
	}

	public void setFlightno_sf(Flight flightno_sf) {
		this.flightno_sf = flightno_sf;
	}

	public City getFromcity_sf() {
		return fromcity_sf;
	}

	public void setFromcity_sf(City fromcity_sf) {
		this.fromcity_sf = fromcity_sf;
	}

	public City getTocity_sf() {
		return tocity_sf;
	}

	public void setTocity_sf(City tocity_sf) {
		this.tocity_sf = tocity_sf;
	}

}
