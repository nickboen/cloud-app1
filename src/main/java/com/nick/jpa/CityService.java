package com.nick.jpa;

import java.util.List;

public interface CityService {

    /**
     * Creates a new person.
     * @param created   The information of the created person.
     * @return  The created person.
     */
    public City create(CityDTO created);
 
    /**
     * Deletes a person.
     * @param personId  The id of the deleted person.
     * @return  The deleted person.
     * @throws PersonNotFoundException  if no person is found with the given id.
     */
    public City delete(Long personId) ;
 
    /**
     * Finds all persons.
     * @return  A list of persons.
     */
    public List<City> findAll();
 
    /**
     * Finds person by id.
     * @param id    The id of the wanted person.
     * @return  The found person. If no person is found, this method returns null.
     */
    public City findById(Long id);
 
    /**
     * Updates the information of a person.
     * @param updated   The information of the updated person.
     * @return  The updated person.
     * @throws PersonNotFoundException  if no person is found with given id.
     */
    public City update(CityDTO updated) ;
    
}
