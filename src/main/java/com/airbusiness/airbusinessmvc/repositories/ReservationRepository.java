package com.airbusiness.airbusinessmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airbusiness.airbusinessmvc.entities.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long>  {
}