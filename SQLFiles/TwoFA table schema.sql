CREATE TABLE twofa
(TwoFA_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 user_id INT,
 user_key varchar(50),
 FOREIGN KEY(user_id) references user_credentials(user_id));