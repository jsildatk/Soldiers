package org.soldiers.repository;

import org.soldiers.model.Item;
import org.soldiers.model.Soldier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findBySoldiers(Soldier soldier);
    List<Item> findBySoldiersNotContains(Soldier soldier);
    List<Item> findAll();
}
