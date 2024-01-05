package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ContentRepository extends ListCrudRepository<Content, Integer> {

    List<Content> findAllByTitleContains(String title);

    @Query("""
            SELECT * FROM Content WHERE status = :status
            """)
    List<Content> findAllByStatusEquals(@Param("status") Status status);

}
