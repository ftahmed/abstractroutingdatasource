package me.murrer.repositories;


import org.springframework.data.repository.CrudRepository;

import me.murrer.models.Shift;


/**
 * Repository for handling {@link Shift} persistence.
 */
public interface ShiftRepository extends CrudRepository<Shift, Long>{

}
