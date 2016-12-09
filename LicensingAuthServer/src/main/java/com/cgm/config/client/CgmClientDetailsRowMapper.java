package com.cgm.config.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandruiulian.cova on 12/6/2016.
 */
public class CgmClientDetailsRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {

        BaseClientDetails details = new BaseClientDetails(rs.getString("CLIENT_ID"), rs.getString("RESOURCE_IDS"), rs.getString("SCOPE"),
                rs.getString("AUTHORIZED_GRANT_TYPES"), rs.getString("AUTHORITIES"), rs.getString("WEB_SERVER_REDIRECT_URI"));
        details.setClientSecret(rs.getString("CLIENT_SECRET"));
        if (rs.getObject("ACCESS_TOKEN_VALIDITY") != null) {
            details.setAccessTokenValiditySeconds(rs.getInt("ACCESS_TOKEN_VALIDITY"));
        }
        if (rs.getObject("REFRESH_TOKEN_VALIDITY") != null) {
            details.setRefreshTokenValiditySeconds(rs.getInt("REFRESH_TOKEN_VALIDITY"));
        }
        String json = rs.getString("ADDITIONAL_INFORMATION");
        if (json != null) {
            try {
                Map<String, Object> jsonObject = new ObjectMapper().readValue(json, Map.class);
                details.setAdditionalInformation(jsonObject);
            }
            catch (Exception e){
                details.setAdditionalInformation(new HashMap<>());
            }
        }
        String scopes = rs.getString("AUTOAPPROVE");
        if (scopes != null) {
            details.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(scopes));
        }
        return details;
    }
}
