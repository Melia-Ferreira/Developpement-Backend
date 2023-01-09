package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "SELECT count(population)"
    + "FROM CITY"
    +"WHERE CITY.COUNTRY_ID = :idPays",
    nativeQuery = true)
    public List<Country> populationPourUnPaysSQL(Integer idPays);

}
