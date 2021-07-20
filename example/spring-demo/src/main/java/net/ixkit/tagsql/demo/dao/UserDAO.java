package net.ixkit.tagsql.demo.dao;

import net.ixkit.orm.Object2SQL;
import net.ixkit.tagsql.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getCountOfUsers() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM User", Integer.class);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM User", new UserRowMapper());
    }


    public void addUser(User user) {
        String sql = Object2SQL.with(User.class).toInsertSQL(user);

        jdbcTemplate.execute(sql);

    }


    public User getUser(final int id) {
        final String query = "SELECT * FROM User WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper());
    }


    public int countUser(User user) {

        final String sql = "SELECT COUNT(*) FROM User WHERE FIRST_NAME = '" + user.getFirstName() + "'";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


}
