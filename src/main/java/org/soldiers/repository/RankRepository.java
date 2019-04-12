package org.soldiers.repository;

import org.soldiers.model.Rank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends CrudRepository<Rank, Long> {
    List<Rank> findAll();
}
