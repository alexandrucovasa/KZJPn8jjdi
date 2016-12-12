package com.cgm.config.token;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created by alexandruiulian.cova on 12/7/2016.
 */
@Service
public class CgmTokenStore extends JdbcTokenStore {

    public CgmTokenStore(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken oAuth2AccessToken) {
        return super.readAuthentication(oAuth2AccessToken);
    }

    @Override
    public OAuth2Authentication readAuthentication(String s) {
        return super.readAuthentication(s);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        super.storeAccessToken(oAuth2AccessToken, oAuth2Authentication);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String s) {
        return super.readAccessToken(s);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken oAuth2AccessToken) {
        super.removeAccessToken(oAuth2AccessToken);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication oAuth2Authentication) {
        super.storeRefreshToken(oAuth2RefreshToken, oAuth2Authentication);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String s) {
        return super.readRefreshToken(s);
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        return super.readAuthenticationForRefreshToken(oAuth2RefreshToken);
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        super.removeRefreshToken(oAuth2RefreshToken);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken oAuth2RefreshToken) {
        super.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        return super.getAccessToken(oAuth2Authentication);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String s, String s1) {
        return super.findTokensByClientIdAndUserName(s, s1);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String s) {
        return super.findTokensByClientId(s);
    }
}
