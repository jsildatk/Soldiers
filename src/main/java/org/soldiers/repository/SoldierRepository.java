package org.soldiers.repository;

import org.soldiers.model.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {
    List<Soldier> findByUserNull();
}
