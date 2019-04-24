use soldiers;

ALTER DATABASE soldiers DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

SET sql_mode='NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS item_soldier;
DROP TABLE IF EXISTS mission_team;
DROP TABLE IF EXISTS mission;
DROP TABLE IF EXISTS soldier;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS `rank`;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TRIGGER IF EXISTS delete_user;

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
    PRIMARY KEY (item_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE role (
	role_id INT AUTO_INCREMENT,
    role VARCHAR(9) UNIQUE NOT NULL,
    PRIMARY KEY (role_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE user (
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
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (rank_id) REFERENCES rank (rank_id),
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
) DEFAULT CHARSET=UTF8;

CREATE TABLE news (
	news_id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    title VARCHAR(30) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    add_date TIMESTAMP,
    PRIMARY KEY (news_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
) DEFAULT CHARSET=UTF8;

DELIMITER //
CREATE TRIGGER delete_user 
AFTER DELETE ON soldier FOR EACH ROW 
BEGIN
	DELETE FROM user WHERE user.user_id = old.soldier_id;
END //
DELIMITER ;

INSERT INTO role (role) VALUES ('ADMIN'), ('COMMANDER'), ('SOLDIER');
INSERT INTO rank (rank) VALUES ('Szeregowy'), ('Kapral'), ('Sierżant'), ('Chorąży'), ('Podporucznik'), ('Porucznik'), ('Kapitan'), ('Major'),
('Podpułkownik'), ('Pułkownik'), ('Generał'), ('Marszałek');
INSERT INTO address (street, city, postal_code) VALUES ('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), 
('Potocka 87', 'Warszawa', '21-340'), ('Marusarzówny 18', 'Gdańsk', '80-126'), ('Słoneczna 10/20', 'Kartuzy', '84-210'),
('Wita Stwosza 57', 'Gdańsk', '80-298'), ('Mickiewicza 1', 'Lębork', '58-963'), ('Potockiego 18', 'Warszawa', '21-879'),
('Zakapowa 98/89', 'Koszalin', '19-888'), ('Marynarska 23/102', 'Gdańsk', '80-126'), ('Wesoła 5', 'Sierakowice', '83-340'),
('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), ('Wesoła 5', 'Sierakowice', '83-340'),
('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), ('Wesoła 5', 'Sierakowice', '83-340');
INSERT INTO team (team) VALUES ('Gromowładni'), ('Gniewni'), ('Bezlitośni');
INSERT INTO user (user_id, role_id, username, password, email) VALUES (0, 1, 'admin', '$2a$10$dA3G9eXf/eEG0kaOfE0tDOm02VrDvawOE.7239nPpdQUAwXHKKGmu', 'admin@wojsko.pl'), 
	(1, 2, 'romek', '$2a$10$dA3G9eXf/eEG0kaOfE0tDOm02VrDvawOE.7239nPpdQUAwXHKKGmu', 'romek@wojsko.pl');
INSERT INTO soldier (user_id, rank_id, address_id, team_id, first_name, last_name, personal_evidence_number, birth_date) VALUES 
(1, 1, 1, 1, 'Roman', 'Mekdzejew', '86024999654', '1986-04-24'), (null, 2, 2, 2, 'Marcin', 'Ruszka', '77092555233', '1977-09-25'), (null, 3, 3, 2 , 'Dariusz', 'Ruszka', '77292555233', '1977-09-25');
INSERT INTO news (user_id, title, content, add_date) VALUES (0, 'Przerwa techniczna', 'Dnia 25.05.2019 od godziny 11:00 
	do godziny 18:00 będzie trwała przerwa techniczna w działaniu strony. Za niedogodności przepraszamy.', '2019-08-04 21:05:56'),
    (0, 'Bal maskowy', 'Dnia 30.04.2019 w sali balowej w Warszawie odbędzie się bal maskowy. Zapisy na adres mailowy "bal@wojsko.pl"', '2019-02-03 16:23:12');
INSERT INTO mission (commander_id, mission, start_date) VALUES (1, 'Burza', '2013-07-22');
INSERT INTO mission (commander_id, mission, start_date, end_date) VALUES (2, 'Pustynna zadyma', '2013-07-22', '2015-02-19');
INSERT INTO mission_team (mission_id, team_id) VALUES (1, 1), (2, 2), (1, 2);