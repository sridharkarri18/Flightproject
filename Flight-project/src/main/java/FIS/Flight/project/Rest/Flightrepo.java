package FIS.Flight.project.Rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import FIS.Flight.project.Entites.Flight;

public interface Flightrepo extends JpaRepository<Flight, String> {
	
	@Query("from Flight f where f.fromcity_flight.code =:from and f.tocity_flight.code =:to")
	List<Flight> gettravellist(@Param("from") String from, @Param("to") String to);

}
