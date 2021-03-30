package com.airbusiness.airbusinessmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airbusiness.airbusinessmvc.entities.Plane;

@Repository
public interface PlaneRepository extends CrudRepository<Plane, Long>  {
}