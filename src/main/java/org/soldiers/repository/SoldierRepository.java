package org.soldiers.repository;

import org.soldiers.model.Soldier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldierRepository extends CrudRepository<Soldier, Long> {
    List<Soldier> findByUserNull();
}
