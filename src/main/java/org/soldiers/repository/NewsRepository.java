package org.soldiers.repository;

import org.soldiers.model.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findAll();
    News findFirstByOrderByAddDateDesc();
}
