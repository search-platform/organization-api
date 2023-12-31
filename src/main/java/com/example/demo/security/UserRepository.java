package com.example.demo.security;

import com.example.demo.dto.SearchResponseDto;
import com.example.demo.dto.SearchResponseType;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public String getEncodedPasswordByLogin(String login) {
        String sql = "SELECT u.password as user_password FROM \"user\" u WHERE u.login = ?";
        String response = null;
        try {
            response = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{login},
                    (rs, rowNum) -> rs.getString("user_password")
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return response;
    }
}
