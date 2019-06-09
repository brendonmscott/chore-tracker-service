CREATE TABLE IF NOT EXISTS account (
    id INTEGER AUTO_INCREMENT,
    family_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS banner_message (
    id INTEGER AUTO_INCREMENT,
    headline_one VARCHAR(255),
    headline_one_text VARCHAR(1024),
    headline_two VARCHAR(255) NOT NULL,
    headline_two_text VARCHAR(1024) NOT NULL,
    headline_three VARCHAR(255) NOT NULL,
    headline_three_text VARCHAR(1024) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS chore (
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    frequency INTEGER NOT NULL,
    reward_id INTEGER,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS chore_assignees (
    id INTEGER AUTO_INCREMENT,
    chore_id INTEGER,
    assignee_id INTEGER,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS login_credentials (
    id INTEGER AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS reward (
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    reward_type INTEGER,
    reward_value INTEGER,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS user (
    id INTEGER AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date VARCHAR(10) NOT NULL,
    points_earned INTEGER,
    money_earned DECIMAL(19,2),
    email VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS user_family_members (
    id INTEGER AUTO_INCREMENT,
    user_id INTEGER,
    family_member_id INTEGER,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS user_roles (
    id INTEGER AUTO_INCREMENT,
    user_id INTEGER,
    role_id INTEGER,
    PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS role (
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB;

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');