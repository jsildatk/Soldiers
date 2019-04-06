use soldiers;

DROP TABLE IF EXISTS item_soldier;
DROP TABLE IF EXISTS mission_team;
DROP TABLE IF EXISTS mission;
DROP TABLE IF EXISTS soldier;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS rank;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_role;

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

CREATE TABLE rank (
	rank_id INT AUTO_INCREMENT,
    rank VARCHAR(40) UNIQUE NOT NULL,
    PRIMARY KEY (rank_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE item (
	item_id INT AUTO_INCREMENT,
    item VARCHAR(30) UNIQUE NOT NULL,
    PRIMARY KEY (item_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE user_role (
	user_role_id INT AUTO_INCREMENT,
    role VARCHAR(9) UNIQUE NOT NULL,
    PRIMARY KEY (user_role_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE user (
	user_id INT AUTO_INCREMENT,
    user_role_id INT NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    password BINARY(60) NOT NULL,
	email VARCHAR(30) UNIQUE NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_role_id) REFERENCES user_role (user_role_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE soldier (
	soldier_id INT AUTO_INCREMENT,
    user_id INT DEFAULT NULL UNIQUE,
    rank_id INT NOT NULL,
    address_id INT NOT NULL,
    team_id INT NOT NULL,
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
	item_soldier_id INT AUTO_INCREMENT,
    item_id INT NOT NULL,
    soldier_id INT NOT NULL,
    PRIMARY KEY (item_soldier_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id),
    FOREIGN KEY (soldier_id) REFERENCES soldier (soldier_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE mission (
	mission_id INT AUTO_INCREMENT,
    commander_id INT NOT NULL,
    mission VARCHAR(40) UNIQUE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    PRIMARY KEY (mission_id),
    FOREIGN KEY (commander_id) REFERENCES soldier (soldier_id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE mission_team (
	mission_team_id INT AUTO_INCREMENT,
    mission_id INT NOT NULL,
    team_id INT NOT NULL,
    PRIMARY KEY (mission_team_id),
    FOREIGN KEY (mission_id) REFERENCES mission (mission_id),
    FOREIGN KEY (team_id) REFERENCES team (team_id)
) DEFAULT CHARSET=UTF8;

INSERT INTO user_role (role) VALUES ('ADMIN'), ('COMMANDER'), ('SOLDIER');
INSERT INTO rank (rank) VALUES ('Szeregowy'), ('Kapral'), ('Sierżant'), ('Chorąży'), ('Podporucznik'), ('Porucznik'), ('Kapitan'), ('Major'),
('Podpułkownik'), ('Pułkownik'), ('Generał'), ('Marszałek');
INSERT INTO address (street, city, postal_code) VALUES ('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), 
('Potocka 87', 'Warszawa', '21-340'), ('Marusarzówny 18', 'Gdańsk', '80-126'), ('Słoneczna 10/20', 'Kartuzy', '84-210'),
('Wita Stwosza 57', 'Gdańsk', '80-298'), ('Mickiewicza 1', 'Lębork', '58-963'), ('Potockiego 18', 'Warszawa', '21-879'),
('Zakapowa 98/89', 'Koszalin', '19-888'), ('Marynarska 23/102', 'Gdańsk', '80-126'), ('Wesoła 5', 'Sierakowice', '83-340'),
('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), ('Wesoła 5', 'Sierakowice', '83-340'),
('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), ('Wesoła 5', 'Sierakowice', '83-340');
INSERT INTO team (team) VALUES ('Gromowładni'), ('Gniewni'), ('Bezlitośni');
INSERT INTO user (user_role_id, username, password, email) VALUES (1, 'admin', '$2a$10$dA3G9eXf/eEG0kaOfE0tDOm02VrDvawOE.7239nPpdQUAwXHKKGmu', 'admin@wojsko.pl');
INSERT INTO soldier (user_id, rank_id, address_id, team_id, first_name, last_name, personal_evidence_number, birth_date) VALUES 
(1, 1, 1, 1, 'Roman', 'Mekdżejew', '86024999654', '1986-04-24'), (null, 2, 2, 2, 'Marcin', 'Ruszka', '77092555233', '1977-09-25');