CREATE TABLE TB_USER(
U_SEQ NUMBER primary key,
NAME VARCHAR2(20),
PASSWORD VARCHAR2(24),
EMAIL VARCHAR2(40),
PHONE VARCHAR2(20),
ROLE VARCHAR2(10)
);


CREATE TABLE TB_GROUP(
G_SEQ NUMBER PRIMARY key,
U_SEQ NUMBER,
NAME VARCHAR2(20),
FOREIGN KEY (U_SEQ) REFERENCES TB_USER (U_SEQ)
);

CREATE TABLE TB_FRIEND(
U_SEQ NUMBER,
F_SEQ NUMBER,
FOREIGN KEY (U_SEQ) REFERENCES TB_USER (U_SEQ),
FOREIGN KEY (F_SEQ) REFERENCES TB_USER (U_SEQ)
);


CREATE TABLE TB_GROUP_TO_FRIEND (
G_SEQ NUMBER,
F_SEQ NUMBER,
FOREIGN KEY (F_SEQ) REFERENCES TB_USER (U_SEQ),
FOREIGN KEY (G_SEQ) REFERENCES TB_GROUP (G_SEQ)
);

CREATE TABLE TB_HISTORY (
H_SEQ NUMBER PRIMARY KEY,
U_SEQ NUMBER,
FLAG VARCHAR2(1),
ACTION_TIME DATE,
F_SEQ NUMBER,
G_SEQ NUMBER,
FOREIGN KEY (U_SEQ) REFERENCES TB_USER (U_SEQ),
FOREIGN KEY (F_SEQ) REFERENCES TB_USER (U_SEQ),
FOREIGN KEY (G_SEQ) REFERENCES TB_GROUP (G_SEQ)
);


CREATE SEQUENCE U_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 100000000
NOCYCLE;

CREATE SEQUENCE F_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 1000000000
NOCYCLE;

CREATE SEQUENCE G_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 1000
NOCYCLE;