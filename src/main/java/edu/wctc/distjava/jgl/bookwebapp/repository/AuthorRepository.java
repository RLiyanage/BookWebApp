package edu.wctc.distjava.jgl.bookwebapp.repository;

import edu.wctc.distjava.jgl.bookwebapp.model.Author;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rasika
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
    
	// example of a projection query
//    @Query("SELECT a.authorName FROM Author a")
//    public abstract Object[] findAllWithNameOnly();
//    public abstract List<Author>findByAuthorName(@Param ("authorName")String authorName);
}
