DROP TABLE IF EXISTS item_soldier;
DROP TABLE IF EXISTS mission_team;
DROP TABLE IF EXISTS mission;
DROP TABLE IF EXISTS soldier;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS `rank`;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS role;

SET NAMES utf8 COLLATE utf8_polish_ci;
SET sql_mode='NO_AUTO_VALUE_ON_ZERO';

CREATE TABLE address (
	address_id INT AUTO_INCREMENT,
    street VARCHAR(40) NOT NULL,
    city VARCHAR(40) NOT NULL,
    postal_code CHAR(6) NOT NULL,
    PRIMARY KEY (address_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE team (
	team_id INT AUTO_INCREMENT,
    team VARCHAR(40) UNIQUE NOT NULL,
    PRIMARY KEY (team_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE `rank` (
	rank_id INT AUTO_INCREMENT,
    `rank` VARCHAR(40) UNIQUE NOT NULL,
    PRIMARY KEY (rank_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE item (
	item_id INT AUTO_INCREMENT,
    item VARCHAR(30) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    image VARCHAR(30) UNIQUE NOT NULL,
    PRIMARY KEY (item_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE role (
	role_id INT AUTO_INCREMENT,
    role VARCHAR(9) UNIQUE NOT NULL,
    PRIMARY KEY (role_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE `user` (
	user_id INT,
    role_id INT NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    password BINARY(60) NOT NULL,
	email VARCHAR(30) UNIQUE NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE soldier (
	soldier_id INT AUTO_INCREMENT,
    user_id INT DEFAULT NULL UNIQUE,
    rank_id INT NOT NULL,
    address_id INT,
    team_id INT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    personal_evidence_number CHAR(11) UNIQUE NOT NULL,
    birth_date DATE NOT NULL,
    PRIMARY KEY (soldier_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (rank_id) REFERENCES `rank` (rank_id),
    FOREIGN KEY (address_id) REFERENCES address (address_id),
    FOREIGN KEY (team_id) REFERENCES team (team_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE item_soldier (
    item_id INT NOT NULL,
    soldier_id INT NOT NULL,
    PRIMARY KEY (item_id, soldier_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id) ON DELETE CASCADE,
    FOREIGN KEY (soldier_id) REFERENCES soldier (soldier_id) ON DELETE CASCADE
) DEFAULT CHARSET=UTF8;

CREATE TABLE mission (
	mission_id INT AUTO_INCREMENT,
    commander_id INT NOT NULL,
    mission VARCHAR(40) UNIQUE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE DEFAULT NULL,
    PRIMARY KEY (mission_id),
    FOREIGN KEY (commander_id) REFERENCES soldier (soldier_id) ON DELETE CASCADE
) DEFAULT CHARSET=UTF8;

CREATE TABLE mission_team (
    mission_id INT NOT NULL,
    team_id INT NOT NULL,
    PRIMARY KEY (mission_id, team_id),
    FOREIGN KEY (mission_id) REFERENCES mission (mission_id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES team (team_id) ON DELETE CASCADE
);

CREATE TABLE news (
	news_id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(30) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    add_date TIMESTAMP,
    PRIMARY KEY (news_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE CASCADE
) DEFAULT CHARSET=UTF8;