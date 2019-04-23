package org.soldiers.repository;

import org.soldiers.model.Mission;
import org.soldiers.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {
    List<Mission> findAll();
    Mission findByMission(String mission);
    List<Mission> findByTeams(Team team);
}
