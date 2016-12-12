package com.cgm.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by Alexandru Covasa.
 */
@Service
public class CgmAuthenticationService implements UserDetailsManager {
    private static final String LOAD_BY_USERNAME_QUERY = "SELECT * FROM CGM_USERS WHERE USER_NAME = ?";
    private static final String CREATE_USER_QUERY = "INSERT INTO CGM_USERS (USER_NAME, PASSWORD, ROLE, LICENCE_ID) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE CGM_USERS SET ROLE = ?, LICENCE_ID = ? WHERE USER_NAME = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM CGM_USERS WHERE USER_NAME = ?";
    private static final String CHANGE_PASSWORD_QUERY = "UPDATE CGM_USERS SET PASSWORD = ? WHERE USER_NAME = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = (UserInfo) jdbcTemplate.queryForObject(LOAD_BY_USERNAME_QUERY, new Object[]{username}, new UserInfoRowMapper());

        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
        UserDetails userDetails = new User(userInfo.getUsername(),
                userInfo.getPassword(), Arrays.asList(authority));
        return userDetails;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        jdbcTemplate.update(CREATE_USER_QUERY, new Object[]{userDetails.getUsername(), userDetails.getPassword(), StringUtils.arrayToCommaDelimitedString(userDetails.getAuthorities().toArray()), ""});
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        //TODO
    }

    @Override
    public void deleteUser(String username) {
        //TODO
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        //TODO
    }

    @Override
    public boolean userExists(String username) {
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails.getUsername() == null || "".equals(userDetails.getUsername())) {
            return false;
        }
        return true;
    }
}
