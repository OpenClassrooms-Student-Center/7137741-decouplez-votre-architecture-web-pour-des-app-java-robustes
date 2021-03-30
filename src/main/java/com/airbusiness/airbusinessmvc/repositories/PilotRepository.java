package com.airbusiness.airbusinessmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airbusiness.airbusinessmvc.entities.Pilot;


@Repository
public interface PilotRepository extends CrudRepository<Pilot, Long>  {
}