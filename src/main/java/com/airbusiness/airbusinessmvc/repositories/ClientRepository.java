package com.airbusiness.airbusinessmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airbusiness.airbusinessmvc.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>  {
}
