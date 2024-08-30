package tn.bna_backend.rowmapper;


import org.springframework.jdbc.core.RowMapper;
import tn.bna_backend.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .phone(resultSet.getString("phone"))
                .bio(resultSet.getString("bio"))
                .imageUrl(resultSet.getString("image_url"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();


    }
}
