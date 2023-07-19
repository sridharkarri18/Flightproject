package FIS.Flight.project.Rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import FIS.Flight.project.Entites.FlightKey;
import FIS.Flight.project.Entites.ScheduledFlight;
import jakarta.transaction.Transactional;

@Transactional
public interface Scheduledrepo extends JpaRepository<ScheduledFlight, FlightKey>{

		
	@Query("select sf.flightno_sf.No from ScheduledFlight sf where sf.key_sf.dep_date=:date and  sf.fromcity_sf.code=:city")
	List<String> scheduledflight(@Param("date") LocalDate date,@Param("city")String city);
	
	@Modifying
	@Query("delete  from ScheduledFlight  sf where sf.key_sf.dep_date >= :date1 and sf.key_sf.dep_date<=:date2")
	int scheduledflightcancel(@Param("date1")LocalDate date1,@Param("date2") LocalDate date2);

}
