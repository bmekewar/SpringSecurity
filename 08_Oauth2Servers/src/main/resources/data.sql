#SELECT * FROM oauthdb.user;
#SELECT * FROM oauthdb.role;
#SELECT * FROM oauthdb.user_role;

INSERT INTO oauthdb.user (user_id, active, email, username, password) VALUES (1,1,'admin@auth.in','admin','admin');
INSERT INTO oauthdb.user (user_id, active, email, username, password) VALUES (2,1,'user@auth.com','user','pass');

INSERT INTO oauthdb.role (role_id, role) VALUES (1,'ADMIN');
INSERT INTO oauthdb.role (role_id, role) VALUES (2,'USER');

INSERT INTO oauthdb.user_role (user_id, role_id) VALUES (1,1);
INSERT INTO oauthdb.user_role (user_id, role_id) VALUES (2,2);
