create table cgm_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  auto_approve VARCHAR(255)
);

alter table  USERS
ADD LICENCE_ID VARCHAR(255);

ALTER TABLE USERS
  RENAME TO CGM_USERS;

