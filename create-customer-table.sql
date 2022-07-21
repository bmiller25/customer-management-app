CREATE DATABASE customerdata;

USE customerdata;

CREATE TABLE customer (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  gender VARCHAR(1),
  address VARCHAR(150),
  phone_number VARCHAR(15),
  name_on_credit_card VARCHAR(45),
  credit_card_number VARCHAR(20),
  expiration_date VARCHAR(4),
  csc VARCHAR(4),
  rewards_member TINYINT,
  rewards_points INT
);