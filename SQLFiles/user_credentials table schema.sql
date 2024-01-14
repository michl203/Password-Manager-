CREATE TABLE user_credentials
(user_id int auto_increment primary key not null,
username varchar(25),
user_password varbinary(250),
user_salt binary(16));


