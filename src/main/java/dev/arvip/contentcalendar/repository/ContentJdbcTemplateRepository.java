package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import dev.arvip.contentcalendar.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs, Integer id) throws SQLException {
        return new Content(
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated", LocalDateTime.class),
                rs.getInt("id"),
                rs.getString("desc"),
                rs.getString("title"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getString("url")
        );
    }

    public List<Content> getAll(){
        String query = "SELECT * FROM Content";
        return jdbcTemplate.query(query, ContentJdbcTemplateRepository::mapRow);
    }

    public Content getById(Integer id) {
        String query = "SELECT * FROM Content WHERE id=?";
        Content content;
        try{
            content = jdbcTemplate.queryForObject(query, new Object[]{id}, ContentJdbcTemplateRepository::mapRow);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
        return content;
    }

    public void createContent(Content content) {
        if (getById(content.id()) != null) throw new ResponseStatusException(HttpStatus.OK);
        String query = "INSERT INTO Content (date_created, desc, title, status, content_type, url) " +
                "VALUES ( ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, content.dateCreated(), content.desc(), content.title(), String.valueOf(content.status()), String.valueOf(content.contentType()), content.url());
    }

    public void updateContent(Content content, Integer id) {
        if (getById(id) == null) {
            createContent(content);
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
        String query = "UPDATE Content SET date_updated=?, desc=?, title=?, status=?, content_type=?, url=? WHERE id=?";
        jdbcTemplate.update(query, content.dateUpdated(), content.desc(), content.title(),
                String.valueOf(content.status()), String.valueOf(content.contentType()), content.url(), id);
    }

    public void deleteContent(Integer id) {
        if (getById(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        String query = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(query, id);
    }
}
