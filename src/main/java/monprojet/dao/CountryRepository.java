package monprojet.dao;

import java.util.List;

import monprojet.dto.PopulationParPays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /** Une méthode qui, pour un pays dont l'ID est passé en paramètre,
     * calcule sa population (somme des populations des villes).
     * @param  ID l'id du pays à traiter
     * @return la population totale du pays en question
     */

    @Query(value = "SELECT SUM(POPULATION)"
    +" FROM CITY"
    +" WHERE COUNTRY_ID=:idPays",
    nativeQuery = true)
    public Integer populationPourUnPaysSQL(int idPays);

    /** Une méthode sans paramètre, qui renvoie une liste (nom du pays, population).
     * Pour cette méthode, vous devrez définir un DTO (Data Transfer Object)
     * qui représente les résultats de la méthode.
     * @return la population totale par pays, sous la forme d'une liste de
     * DTO PopulationParPays
     */
    @Query(value ="SELECT COUNTRY.NAME AS Pays, SUM(CITY.POPULATION) AS Population"
    +" FROM COUNTRY"
    +" INNER JOIN CITY ON COUNTRY.ID=CITY.COUNTRY_ID"
            + " GROUP BY COUNTRY.NAME",
            nativeQuery = true)
    public List<PopulationParPays> populationParPays();


}
