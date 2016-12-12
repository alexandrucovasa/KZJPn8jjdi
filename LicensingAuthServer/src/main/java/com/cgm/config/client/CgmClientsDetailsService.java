package com.cgm.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by alexandruiulian.cova on 12/6/2016.
 */

@Service
public class CgmClientsDetailsService implements ClientDetailsService {

    private static final String CLIENT_FIELDS_FOR_UPDATE = " resource_ids, scope, "
            + "authorized_grant_types, WEB_SERVER_REDIRECT_URI, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;

    private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
            + " from cgm_client_details";

    private static final String FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    private static final String SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    private static final String INSERT_STATEMENT = "insert into cgm_client_details (" + CLIENT_FIELDS
            + ", client_id) values (?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_STATEMENT = "update cgm_client_details " + "set "
            + CLIENT_FIELDS_FOR_UPDATE.replaceAll(", ", "=?, ") + "=? where client_id = ?";

    private static final String UPDATE_SECRET_STATEMENT = "update cgm_client_details "
            + "set client_secret = ? where client_id = ?";

    private static final String DELETE_STATEMENT = "delete from cgm_client_details where client_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        ClientDetails clientDetails = (ClientDetails) jdbcTemplate.queryForObject(SELECT_STATEMENT, new Object[]{clientId}, new CgmClientDetailsRowMapper());
        return clientDetails;
    }


}
