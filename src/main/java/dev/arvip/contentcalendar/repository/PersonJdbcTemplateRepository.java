package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.model.Sex;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersonJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Person mapRow(ResultSet rs, Integer id) throws SQLException {
        return new Person(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("age"),
                Sex.valueOf(rs.getString("sex").trim()),
                rs.getString("phone_number"),
                rs.getString("address"),
                rs.getInt("person_id")
        );
    }

    public List<Person> getAll(){
        String query = "SELECT * FROM Person";
        return jdbcTemplate.query(query, PersonJdbcTemplateRepository::mapRow);
    }

    public Person getById(Integer id){
        System.out.println(id);
        String query = "SELECT * FROM Person WHERE person_id=?";
        try{
            return jdbcTemplate.queryForObject(query, PersonJdbcTemplateRepository::mapRow, id);
        } catch (EmptyResultDataAccessException e){
            System.out.println("Caught");
            return null;
        }
    }

    public void addPerson(Person person){
        if (getById(person.personId()) != null) throw new ResponseStatusException(HttpStatus.OK);
        String query = "INSERT INTO Person (first_name, last_name, age, sex, phone_number, address)" +
                " VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, person.firstName(), person.lastName(), person.age(), String.valueOf(person.sex()), person.phoneNumber(), person.address());
    }

    public void updatePerson(Person person, Integer id){
        if (getById(id) == null) {
            addPerson(person);
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
        String query = "UPDATE Person SET first_name=?, last_name=?, age=?, sex=?, phone_number=?, address=? WHERE person_id=?;";
        jdbcTemplate.update(query, person.firstName(), person.lastName(), person.age(), String.valueOf(person.sex()), person.phoneNumber(), person.address(), person.personId());
    }

    public void deletePerson(Integer id){
        String query = "DELETE FROM Person WHERE person_id=?";
        jdbcTemplate.update(query, id);
    }


}
