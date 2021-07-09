package net.ixkit.tagsql.demo.dao;

import net.ixkit.tagsql.demo.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final User user = new User();

        user.setId(rs.getInt("ID"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setBirthday(rs.getDate("Birthday"));
        user.setAddress(rs.getString("ADDRESS"));
        user.setRank(rs.getInt("rank"));
        user.setScore(rs.getDouble("score"));
        return user;
    }
}
