CREATE TABLE IF NOT EXISTS Content (
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    id int NOT NULL AUTO_INCREMENT,
    desc TEXT,
    title VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    url VARCHAR(255),
    primary key (id)
);

--INSERT INTO Content (date_created, date_updated, desc, title, status, content_type, url)
--VALUES (CURRENT_TIMESTAMP, null, 'First init DB row item', 'First DB content', 'IDEA', 'ARTICLE', null);
--
--CREATE TABLE IF NOT EXISTS Person (
--    first_name CHAR(50) NOT NULL,
--    last_name CHAR(50) NOT NULL,
--    age INTEGER NOT NULL,
--    sex CHAR(10) NOT NULL,
--    phone_number TEXT NOT NULL,
--    address TEXT NOT NULL,
--    person_id int NOT NULL AUTO_INCREMENT,
--    primary key (person_id)
--);
--
--INSERT INTO Person (first_name, last_name, age, sex, phone_number, address)
--VALUES ('Arvind', 'Purushotham', 32, 'MALE', '+1-990-2930-265', 'Monroe Way, Thornton');