package com.cgm.config.authentication;

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
 * Created by alexandruiulian.cova on 12/6/2016.
 */
@Service
public class CgmAuthenticationService implements UserDetailsManager {

    private JdbcTemplate jdbcTemplate;

    public CgmAuthenticationService(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(loadByUserNameQuery(), new Object[] {s}, new UserInfoRowMapper());

        GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
        UserDetails userDetails = new User(userInfo.getUsername(),
                userInfo.getPassword(), Arrays.asList(authority));
        return userDetails;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        jdbcTemplate.update(createUserQuery(), new Object[]{userDetails.getUsername(), userDetails.getPassword(), StringUtils.arrayToCommaDelimitedString(userDetails.getAuthorities().toArray()), ""});
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        //TODO
    }

    @Override
    public void deleteUser(String s) {
        //TODO
    }

    @Override
    public void changePassword(String s, String s1) {
        //TODO
    }

    @Override
    public boolean userExists(String s) {
        UserDetails userDetails = loadUserByUsername(s);
        if (userDetails.getUsername() == null || "".equals(userDetails.getUsername())) {
            return false;
        }
        return true;
    }


    private String loadByUserNameQuery() {
        return "SELECT * FROM CGM_USERS WHERE USER_NAME = ?";
    }

    private String createUserQuery() {
        return "INSERT INTO CGM_USERS (USER_NAME, PASSWORD, ROLE, LICENCE_ID) VALUES (?, ?, ?, ?)";
    }

    private String updateUserQuery() {
        return "UPDATE CGM_USERS SET ROLE = ?, LICENCE_ID = ? WHERE USER_NAME = ?";
    }

    private String deleteUserQuery() {
        return "DELETE FROM CGM_USERS WHERE USER_NAME = ?";
    }

    private String changePasswordQuery() {
        return "UPDATE CGM_USERS SET PASSWORD = ? WHERE USER_NAME = ?";
    }
}
