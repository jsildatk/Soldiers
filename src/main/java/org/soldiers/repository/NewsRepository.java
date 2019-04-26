package org.soldiers.repository;

import org.soldiers.model.News;
import org.soldiers.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    List<News> findAll();
    News findFirstByOrderByAddDateDesc();
    News findByTitle(String title);
    List<News> findByUser(User user);
}
