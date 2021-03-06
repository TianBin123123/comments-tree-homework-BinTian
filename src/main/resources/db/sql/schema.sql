/**
 * table USER
 */
CREATE TABLE USER (
   ID           INTEGER         PRIMARY KEY     AUTOINCREMENT,
   USERNAME     VARCHAR(20)     NOT NULL,
   EMAIL        VARCHAR(256)    NOT NULL,
   PASSWORD     VARCHAR(32)     NOT NULL 
);
-- index on USER
CREATE UNIQUE INDEX IDX_USER_USERNAME on USER (USERNAME);
CREATE UNIQUE INDEX IDX_USER_EMAIL on USER (EMAIL);


/**
 * table COMMENTS
 */
CREATE TABLE COMMENTS (
   ID           INTEGER         PRIMARY KEY     AUTOINCREMENT,
   P_ID         INTEGER,
   CONTENT      VARCHAR(200)    NOT NULL,
   USERNAME     VARCHAR(20)     NOT NULL,
   TIME         DATETIME        NOT NULL
);
-- index on COMMENTS
CREATE INDEX IDX_COMMENTS_PID on COMMENTS (P_ID);
CREATE INDEX IDX_COMMENTS_TIME on COMMENTS (TIME);


