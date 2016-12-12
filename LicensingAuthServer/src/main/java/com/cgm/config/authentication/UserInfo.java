package com.cgm.config.authentication;

/**
 * Created by Alexandru Covasa.
 */
public class UserInfo {

    private String username;
    private String password;
    private String role;
    private String licenceId;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String role, String licenceId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.licenceId = licenceId;
    }

    public String getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(String licenceId) {
        this.licenceId = licenceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
