DELETE FROM authorities;
DELETE FROM users;

INSERT INTO users (username, password, enabled) values ('user', 'password', true);
INSERT INTO users (username, password, enabled) values ('admin', 'admin', true);
INSERT INTO authorities (username, authority) values ('user', 'USER');
INSERT INTO authorities (username, authority) values ('admin', 'ADMIN');
