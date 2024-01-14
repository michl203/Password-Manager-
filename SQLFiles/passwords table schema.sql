CREATE TABLE passwords
(password_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
 user_ID INT,
 website varchar(25),
 w_username varchar(25),
 w_password varcharacter(300),
 FOREIGN KEY(user_ID) references user_credentials(user_ID));