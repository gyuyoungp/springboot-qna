INSERT INTO USER (ID, USER_ID, EMAIL, PASSWORD, NAME) VALUES (1, 'test', 'hello@test.com', '1234', '규영');
INSERT INTO QUESTION (ID, WRITER_ID, TITLE, CONTENTS, CREATE_DATE) VALUES (1, 1, '테스트', '1234', CURRENT_TIMESTAMP());
INSERT INTO QUESTION (ID, WRITER_ID, TITLE, CONTENTS, CREATE_DATE) VALUES (2, 1, '두번째', '예~!!!', CURRENT_TIMESTAMP());