package FIS.Flight.project.Rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import FIS.Flight.project.Entites.City;

public interface Cityrepo  extends JpaRepository<City, String>{
	
	@Query("select name from City")
	List<String> citynames();

}
