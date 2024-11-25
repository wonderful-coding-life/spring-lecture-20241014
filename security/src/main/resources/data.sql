--for JdbcUserDetailsManager
INSERT INTO users(username, password, enabled) VALUES('user', '$2a$12$CQaVq/t/HSn6YsB5I3W1iupxuej9DMaOYtJlt9uvHmZfrj9hx8CBC', TRUE);
INSERT INTO users(username, password, enabled) VALUES('admin', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e', TRUE);

--for JdbcUserDetailsManager
INSERT INTO authorities(username, authority) VALUES('user', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('admin', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('admin', 'ROLE_ADMIN');

--full customization
INSERT INTO member(name, email, password) VALUES('윤서준', 'SeojunYoon@hanbit.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO member(name, email, password) VALUES('윤광철', 'KwangcheolYoon@hanbit.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO member(name, email, password) VALUES('공미영', 'MiyeongKong@hanbit.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO member(name, email, password) VALUES('김도윤', 'DoyunKim@hanbit.co.kr', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e');
INSERT INTO authority(authority, member_id) VALUES('ROLE_USER', 1);
INSERT INTO authority(authority, member_id) VALUES('ROLE_USER', 2);
INSERT INTO authority(authority, member_id) VALUES('ROLE_ADMIN', 2);
