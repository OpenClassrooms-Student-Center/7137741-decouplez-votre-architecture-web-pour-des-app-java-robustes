package com.airbusiness.airbusinessmvc.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


import com.airbusiness.airbusinessmvc.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>  {
	@Query("SELECT c FROM Client c WHERE c.outstandingBalance > 0.0")
	List<Client> findByOustandingBalanceGreaterThan(double value);
}
