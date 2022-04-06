INSERT INTO account(name, email) VALUES('test1', 'test1@example.com');
INSERT INTO account(name, email) VALUES('test2', 'test2@example.com');
INSERT INTO account(name, email) VALUES('test3', 'test3@example.com');
INSERT INTO account(name, email) VALUES('test4', 'test4@example.com');
INSERT INTO account(name, email) VALUES('test5', 'test5@example.com');

INSERT INTO quote(account_id, text) VALUES(1, 'quote text 1_1');
INSERT INTO quote(account_id, text) VALUES(1, 'quote text 1_2');
INSERT INTO quote(account_id, text) VALUES(1, 'quote text 1_3');

INSERT INTO quote(account_id, text) VALUES(2, 'quote text 2_1');
INSERT INTO quote(account_id, text) VALUES(2, 'quote text 2_2');

INSERT INTO quote(account_id, text) VALUES(3, 'quote text 3_1');
INSERT INTO quote(account_id, text) VALUES(4, 'quote text 4_1');
INSERT INTO quote(account_id, text) VALUES(5, 'quote text 5_1');