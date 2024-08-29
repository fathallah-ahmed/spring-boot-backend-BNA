package tn.bna_backend.repository.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.bna_backend.domain.Role;
import tn.bna_backend.domain.User;


import tn.bna_backend.exception.ApiException;
import tn.bna_backend.repository.RoleRepository;
import tn.bna_backend.repository.userRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static tn.bna_backend.enumeration.RoleType.ROLE_USER;
import static tn.bna_backend.enumeration.VerificationType.ACCOUNT;
import static tn.bna_backend.query.UserQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class userRepositoryImpl<T extends User> implements userRepository<T> {

    private final NamedParameterJdbcTemplate jdbc;
    public final RoleRepository<Role> roleRepository;
    public final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    @Override
    public User create(User user) {
        if(getEmailCount(user.getEmail().trim().toLowerCase()) > 0) throw new ApiException( "Email already in use. Please use a different email and try again.");
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);

            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
          //  roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());
        //String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(),ACCOUNT.getType());
       // jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY ,Map.of("userId()",user.getId(),"url",verificationUrl));
        //emailService.sendVerificationUrl(user.getFirstName(),user.getEmail(),verificationUrl,ACCOUNT);
       // user.setEnabled(false);
       // user.setNotLocked(true);

        return user;
        }

        catch (Exception e) {
            // Log the exception and rethrow as ApiException
            log.error("Error creating user", e);
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()))
        ;
    }


    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY , Map.of("email",email),Integer.class);
    }




    @Override
    public Collection<T> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public T get(Long id) {
        return null;
    }

    @Override
    public T update(T data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/"+ type + "/"+ key).toUriString();
    }
}
