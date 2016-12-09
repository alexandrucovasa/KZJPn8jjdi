package com.cgm.config.authentication;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexandruiulian.cova on 12/6/2016.
 */
public class UserInfoRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        UserInfo userInfo = new UserInfo(resultSet.getString("USER_NAME"), resultSet.getString("PASSWORD"), resultSet.getString("ROLE"), resultSet.getString("LICENCE_ID"));
        return userInfo;
    }
}
