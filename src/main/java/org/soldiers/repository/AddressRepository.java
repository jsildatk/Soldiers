package org.soldiers.repository;

import org.soldiers.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
    List<Address> findByCity(String city);
}
