use soldiers;

SET NAMES utf8 COLLATE utf8_polish_ci;
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
DROP TABLE IF EXISTS `user`;
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
    description TEXT NOT NULL,
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

DELIMITER //
CREATE TRIGGER delete_user 
AFTER DELETE ON soldier FOR EACH ROW 
BEGIN
	DELETE FROM `user` WHERE `user`.user_id = old.soldier_id;
END //
DELIMITER ;

INSERT INTO role (role) VALUES ('ADMIN'), ('COMMANDER'), ('SOLDIER');
INSERT INTO `rank` (`rank`) VALUES ('Szeregowy'), ('Kapral'), ('Sierżant'), ('Chorąży'), ('Podporucznik'), ('Porucznik'), ('Kapitan'), ('Major'),
('Podpułkownik'), ('Pułkownik'), ('Generał'), ('Marszałek');
INSERT INTO address (street, city, postal_code) VALUES ('Wesoła 5', 'Sierakowice', '83-340'), ('Marynarska 23/102', 'Gdańsk', '80-126'), 
('Potocka 87', 'Warszawa', '21-340'), ('Marusarzówny 18', 'Gdańsk', '80-126'), ('Słoneczna 10/20', 'Kartuzy', '84-210'),
('Wita Stwosza 57', 'Gdańsk', '80-298'), ('Mickiewicza 1', 'Lębork', '58-963'), ('Potockiego 18', 'Warszawa', '21-879'),
('Zakapowa 98/89', 'Koszalin', '19-888'), ('Lema 87', 'Gdańsk', '80-126'), ('Paracka 2/12', 'Kraków', '23-230'),
('Weselska 8/23', 'Sierakowice', '83-340'), ('Zakopiańska 11/11', 'Koszalin', '19-887'), ('Dworcowa 21', 'Sierakowice', '83-340'),
('Rumuńska 55', 'Gdańsk', '80-121'), ('Spacerowa 7', 'Gdynia', '89-111'), ('Spacerowa 2/4', 'Sierakowice', '83-340');
INSERT INTO team (team) VALUES ('Gromowładni'), ('Gniewni'), ('Bezlitośni'), ('Przepotężni'), ('Masakratorzy'), ('Traperzy');
INSERT INTO `user` (user_id, role_id, username, password, email) VALUES (0, 1, 'admin_test', '$2a$10$dA3G9eXf/eEG0kaOfE0tDOm02VrDvawOE.7239nPpdQUAwXHKKGmu', 'admin@wojsko.pl')
	, (1, 2, 'commander_test', '$2a$10$dA3G9eXf/eEG0kaOfE0tDOm02VrDvawOE.7239nPpdQUAwXHKKGmu', 'commander@wojsko.pl')
    , (2, 3, 'soldier_test', '$2a$10$dA3G9eXf/eEG0kaOfE0tDOm02VrDvawOE.7239nPpdQUAwXHKKGmu', 'soldier@wojsko.pl');
INSERT INTO soldier (user_id, rank_id, address_id, team_id, first_name, last_name, personal_evidence_number, birth_date) VALUES 
(1, 1, 1, 1, 'Roman', 'Mekdżejew', '86024999654', '1986-04-24'), (2, 2, 2, 2, 'Marcin', 'Ruszka', '77092555233', '1977-09-25'), (null, 3, 3, 2 , 'Dariusz', 'Ruszka', '77292555233', '1977-09-25')
, (null, 4, 4, 4, 'Marek', 'Krzak', '88082812345', '1988-08-28'), (null, 5, 5, 5, 'Jerzy', 'Middle', 90070398876, '1990-07-03'), (null, 6, 6, 6, 'Maryla', 'Rodowicz', '68032887651', '1968-03-28');
INSERT INTO news (user_id, title, content, add_date) VALUES (0, 'Przerwa techniczna', 'Dnia 25.05.2019 od godziny 11:00 
	do godziny 18:00 będzie trwała przerwa techniczna w działaniu strony. Za niedogodności przepraszamy.', '2019-03-04 21:05:56'),
    (1, 'Bal maskowy', 'Dnia 30.04.2019 w sali balowej w Warszawie odbędzie się bal maskowy. Zapisy na adres mailowy "bal@wojsko.pl"', '2019-02-03 16:23:12');
INSERT INTO mission (commander_id, mission, start_date) VALUES (1, 'Burza', '2013-07-22'), (1, 'Komuna paryska', '2017-10-19'), (3, 'Desant', '2015-01-03');
INSERT INTO mission (commander_id, mission, start_date, end_date) VALUES (2, 'Pustynna zadyma', '2013-07-22', '2015-02-19');
INSERT INTO mission_team (mission_id, team_id) VALUES (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 5), (4, 2), (4, 6);
INSERT INTO item (item, description) VALUES ('AK-47', 'Broń jest niezwykle trwała, niezawodna i odporna na zabrudzenia oraz zaniedbania eksploatacyjne. Jest też prosta w obsłudze i tania w produkcji, przez co idealnie nadaje się do masowej produkcji i wykorzystywania nawet przez słabo wyszkolone oddziały. Do wad zalicza się dość słabą ergonomię i niewielką celność na dystansach ponad 300 metrów. Wziąwszy jednak pod uwagę, że w 85% przypadków z broni tego typu strzela się na odległości poniżej 300 metrów, wada ta nie ma większego znaczenia. Nie licząc produkcji licencyjnej, z konstrukcji karabinka AK bezpośrednio lub pośrednio wywodzą się takie bronie jak fińskie karabiny rodziny Valmet M62 i Sako M95, izraelskie Galile i polskie Beryle.')
, ('Noktowizor', 'Nagły błysk światła (np. światło latarki skierowane na obiektyw noktowizora) mógł trwale uszkodzić delikatne elementy układu wzmacniającego światło szczątkowe. Dzisiejsze noktowizory są małe, niezawodne, o niewielkim poborze prądu i bardzo skuteczne. Dzięki nim nowoczesna armia może prowadzić sprawne działania w warunkach nocnych.
Cywilne zastosowanie noktowizorów to wyposażenie dla myśliwych, wspomaganie nocnego monitoringu w budynkach oraz realizacja filmów przyrodniczych w warunkach nocnych, bez płoszenia zwierząt sztucznym oświetleniem. Podobne zastosowanie posiada celownik noktowizyjny.')
, ('Dron', 'Urządzenia tego rodzaju, bez żadnego wyposażenia, już od czasów I wojny światowej stosowane były do trenowania oddziałów przeciwlotniczych jako latające cele. Latające bomby V-1 stosowane przez Niemców do bombardowania Londynu czy też pociski manewrujące też zaliczane mogłyby być do kategorii UAV, niemniej pojazdy tego typu są bronią samą w sobie i nie mogą być – w odróżnieniu od BSP – powtórnie wykorzystane.')
, ('M4', 'Początkowo M4 był używany przez żołnierzy jednostek specjalnych, z czasem stał się popularną bronią także w jednostkach zwykłej piechoty, jako uzbrojenie młodszych oficerów, obsług broni ciężkiej i coraz częściej szeregowych żołnierzy. M4 stał się także następcą pistoletów maszynowych M3 używanych przez załogi czołgów i pojazdów opancerzonych.')
, ('Spadochron', 'Statek powietrzny, który wykorzystując opór powietrza do zmniejszenia prędkości opadania w atmosferze, pozwala na bezpieczne wylądowanie podwieszonego skoczka, sprzętu lub ładunku.')
, ('C4', 'Rodzaj plastycznego materiału wybuchowego. Składa się z właściwego materiału wybuchowego, spoiwa, masy plastycznej i (w ostatnich czasach) substancji znakujących. Materiałem wybuchowym w C4 jest heksogen (heksahydro-1,3,5-trinitro-1,3,5-triazyna), który stanowi ponad 90% masy C4. Spoiwem jest poliizobutylen (ok. 2%), a plastyfikatorem jest sebacynian di(2-etyloheksylu) (ok. 5%). Dodaje się również niewielkie ilości (ok. 1,5%) oleju silnikowego. Czasem jako spoiwo stosowany jest adypinian dioktylu (DOA). W USA substancją znakującą jest DMDNB (2,3-dimetylo-2,3-dinitrobutan).')
, ('USP' ,'HK USP jest bronią samopowtarzalną. Zasada działania oparta o krótki odrzut lufy. Mechanizm spustowy z kurkowym mechanizmem uderzeniowym. Pistolet może być wyposażony w bezpiecznik nastawny i zwalniacz kurka. USP jest zasilany z wymiennego, dwurzędowego magazynka pudełkowego o pojemności zależnej od rodzaju naboju. Zatrzask magazynka po obu stronach chwytu pistoletowego. Po wystrzeleniu ostatniego naboju z magazynka zamek zatrzymuje się w tylnym położeniu.')
, ('M82A1', 'W roku 2012 zanotowano najdalsze potwierdzone trafienie z karabinu M82A1 wynoszące 2815 metrów. Strzał oddał stacjonujący w Afganistanie (prowincja Helmand) australijski zespół snajperski z kompanii Delta 2. pułku Commando (2 Cdo Regt) podczas działań obserwacyjnych dostrzegł oddział talibów. Jedna z wystrzelonych kul trafiła dowódcę oddziału talibów. Jest to drugi z najdalszych oddanych do tej pory skutecznych strzałów (najdalszy padł z karabinu McMillan TAC-50). Nazwisko strzelca pozostaje nieznane.');
INSERT INTO item_soldier (item_id, soldier_id) VALUES (1, 1), (1, 2), (2, 1), (3, 1), (1, 4), (4, 4);