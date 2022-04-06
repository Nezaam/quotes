CREATE TABLE IF NOT EXISTS account(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    email VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS quote (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    score INT DEFAULT 0,
    text TEXT
);

CREATE TABLE IF NOT EXISTS vote (
    account_id BIGINT NOT NULL,
    quote_id BIGINT NOT NULL,
    vote INT NOT NULL,
    PRIMARY KEY(account_id, quote_id)
);

ALTER TABLE quote
    ADD FOREIGN KEY (account_id) REFERENCES account(id);

ALTER TABLE vote
    ADD FOREIGN KEY (account_id) REFERENCES account(id);

ALTER TABLE vote
    ADD FOREIGN KEY (quote_id) REFERENCES quote(id);