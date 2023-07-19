package FIS.Flight.project.Entites;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FlightKey implements Serializable {



	@Column(name = "Flight_no")
	private String flightno;

	@Column(name = "Departure_date")
	private LocalDate dep_date;

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}
	public LocalDate getDep_date() {
		return dep_date;
	}
	public void setDep_date(LocalDate dep_date) {
		this.dep_date = dep_date;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(dep_date, flightno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightKey other = (FlightKey) obj;
		return Objects.equals(dep_date, other.dep_date) && Objects.equals(flightno, other.flightno);
	}


}