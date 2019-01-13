CREATE TABLE PROJECTS(
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    created_time DATE,
    updated_time DATE
);

CREATE SEQUENCE project_id_seq 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE PACKAGES (
    id INT PRIMARY KEY,
    project_id INT,
    name VARCHAR(75) NOT NULL,
    type VARCHAR(50) NOT NULL,
    created_time DATE,
    updated_time DATE,
    CONSTRAINT FK_PKG_PROJ FOREIGN KEY(project_id) REFERENCES PROJECTS(id)
);

CREATE SEQUENCE package_id_seq 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


CREATE TABLE FILES (
    id INT PRIMARY KEY,
    project_id INT,
    package_id INT,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    content CLOB,
    created_time DATE,
    updated_time DATE,
    CONSTRAINT FK_FILE_PROJ FOREIGN KEY(project_id) REFERENCES PROJECTS(id),
    CONSTRAINT FK_FILE_PKG FOREIGN KEY(package_id) REFERENCES PACKAGES(id)
);

 
--------------------------------------------------------------------------------------------------------------------------------
INSERT INTO PROJECTS (id, name, type, created_time, updated_time) VALUES( project_id_seq.nextval, 'Algorithm Compendium', 'Java', SYSDATE, SYSDATE );

COMMIT;

SELECT * FROM PROJECTS;

SELECT * FROM PACKAGES;

SELECT * FROM FILES;

SELECT file_id_seq.nextval FROM DUAL;

(SELECT * FROM PROJECTS)
UNION ALL
(SELECT id, name, type, created_time, updated_time FROM files WHERE project_id IS NULL AND package_id IS NULL);

( SELECT * FROM packages WHERE project_id = 2 ) UNION ALL ( SELECT id, project_id, name, type, created_time, updated_time FROM files WHERE project_id = 2 AND package_id IS NULL );


( SELECT * FROM Files WHERE package_id = 2 ) UNION ALL ( SELECT id, project_id, name, type, created_time, updated_time FROM files WHERE project_id = 2 AND package_id IS NULL );


--------------------------------------------------------------------------------------------------------------------------------
DROP TABLE users;

CREATE TABLE users (
    id INT PRIMARY KEY,
    first_nm VARCHAR(15),
    last_nm VARCHAR(15),
    email VARCHAR(30)
);

INSERT INTO users VALUES ( 0000, 'defualt', 'default', null );

CREATE TABLE nodes (
    id INT PRIMARY KEY,
    user_id INT,
    parent_id int,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    content CLOB,
    created_time DATE,
    updated_time DATE,
    CONSTRAINT nodes_to_users FOREIGN KEY(user_id) REFERENCES users(id) 
);

CREATE SEQUENCE node_id_seq 
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


DROP TABLE node_prop_mst;

CREATE TABLE node_prop_mst (
    prop_id INT PRIMARY KEY,
    prop_desc VARCHAR(200)
);

CREATE SEQUENCE node_prop_mst_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE node_properties (
    node_id INT,
    prop_id INT,
    prop_value VARCHAR(200),
    CONSTRAINT FK_node_prop_to_node FOREIGN KEY(node_id) REFERENCES nodes(id),
    CONSTRAINT FK_node_prop_to_prop_mst FOREIGN KEY(prop_id) REFERENCES node_prop_mst(prop_id)
);





-------------------------------------------------------------------------------------------------------------------------------

SELECT * FROM nodes;

INSERT INTO nodes ( id, user_id, parent_id, name, type, content, created_time, updated_time ) VALUES ( node_id_seq.NEXTVAL, 0000,null, ' ',' ', null, SYSDATE, SYSDATE );

-------------------------------------------------------------------------------------------------------------------------------





