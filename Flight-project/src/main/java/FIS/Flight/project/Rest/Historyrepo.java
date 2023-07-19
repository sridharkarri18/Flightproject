package FIS.Flight.project.Rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import FIS.Flight.project.Entites.FlightHistory;
import FIS.Flight.project.Entites.FlightKey;

public interface Historyrepo  extends JpaRepository<FlightHistory, FlightKey>{
	
	@Query("from FlightHistory fh join fetch fh.flightno_fh f where datediff(minute,f.arr_time,fh.arrtime_fh)=:minutes")
	List<FlightHistory> delayedFlights(@Param("minutes")int minutes);

	



}
