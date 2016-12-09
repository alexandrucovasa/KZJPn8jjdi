package com.cgm.config.dto;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Created by alexandruiulian.cova on 12/9/2016.
 */
public class Licence {

    private String licenceId;
    private OAuth2AccessToken oAuth2AccessToken;

    private String customerId;
    private String productId;

    public Licence(String licenceId, OAuth2AccessToken oAuth2AccessToken) {
        this.licenceId = licenceId;
        this.oAuth2AccessToken = oAuth2AccessToken;
    }

    public String getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(String licenceId) {
        this.licenceId = licenceId;
    }

    public OAuth2AccessToken getoAuth2AccessToken() {
        return oAuth2AccessToken;
    }

    public void setoAuth2AccessToken(OAuth2AccessToken oAuth2AccessToken) {
        this.oAuth2AccessToken = oAuth2AccessToken;
    }
}
