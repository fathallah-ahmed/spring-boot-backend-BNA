package tn.bna_backend.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import tn.bna_backend.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {


    @Override
    public Role mapRow(ResultSet resaultSet, int rowNum) throws SQLException {
        return Role.builder()
                .id(resaultSet.getLong("id"))
                .name(resaultSet.getString("name"))
                .permission(resaultSet.getString("permission")
                ).build();
    }
}
