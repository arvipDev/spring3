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

INSERT INTO content (date_created, date_updated, desc, title, status, content_type, url)
VALUES (CURRENT_TIMESTAMP, null, 'First init DB row item', 'First DB content', 'IDEA', 'ARTICLE', null)