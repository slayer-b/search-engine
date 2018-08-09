package com.my.server.repository;

import com.my.server.model.Text;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends CrudRepository<Text, Long> {

    @Query("select t.text from Text t inner join t.words w where w.word in (:wordList)")
    List<String> findByWordIn(@Param("wordList") List<String> wordList);

}
