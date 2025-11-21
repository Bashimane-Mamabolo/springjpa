package com.bash.springjpa.repositories;

import com.bash.springjpa.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    Iterable<Author> findAllByAgeLessThan(int age);

    @Query("SELECT a FROM Author a WHERE a.age > ?1") // ?1, it's the first parameter we have provided
    Iterable<Author> getAuthorsWithAgeThatIsGreaterThan(int age);
}
