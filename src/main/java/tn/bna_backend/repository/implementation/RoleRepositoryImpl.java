package tn.bna_backend.repository.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tn.bna_backend.domain.Role;
import tn.bna_backend.exception.ApiException;
import tn.bna_backend.repository.RoleRepository;
import tn.bna_backend.rowmapper.RoleRowMapper;
import static java.util.Map.of;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static tn.bna_backend.enumeration.RoleType.ROLE_USER;
import static tn.bna_backend.query.RoleQuery.*;

@Repository
@Slf4j
@RequiredArgsConstructor
public class    RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection list() {
        return List.of();
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("adding role {} to user {}", roleName, userId);
        try {
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY, Map.of("userId", userId, "roleId", Objects.requireNonNull(role).getId()));

        } catch (EmptyResultDataAccessException exception) {

            throw new ApiException("no role found by name " + ROLE_USER.name());
        } catch (Exception exception)
        {throw new ApiException("An error occurred!");}


    }

    @Override
    public Role getRoleByUserId(Long userId) {
        log.info("Fetching role for user id: {}", userId);
        try {Role result = jdbc.queryForObject(SELECT_ROLE_BY_ID_QUERY, of("id", userId), new RoleRowMapper());
            System.out.print(result);
            return result;

        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No role found by name: " + ROLE_USER.name());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }


    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }
}
