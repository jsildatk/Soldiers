package org.soldiers.repository;

import org.soldiers.model.Soldier;
import org.soldiers.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldierRepository extends CrudRepository<Soldier, Long> {
    List<Soldier> findByUserNull();
    List<Soldier> findAll();
    List<Soldier> findByLastName(String lastName);
    List<Soldier> findByTeam(Team team);
}
