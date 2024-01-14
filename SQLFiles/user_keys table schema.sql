CREATE TABLE user_key
(key_id integer auto_increment primary key not null,
 user_id integer, 
 user_key varbinary(255),
 FOREIGN KEY (user_id) references user_credentials (user_id));
