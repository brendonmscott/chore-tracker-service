ALTER TABLE chore
    DROP COLUMN completed;

ALTER TABLE chore
    DROP COLUMN approved;

CREATE TABLE IF NOT EXISTS completed_chore (
    id INTEGER AUTO_INCREMENT,
    assignee_id INTEGER NOT NULL,
    chore_id INTEGER NOT NULL,
    completed_date VARCHAR(10) NOT NULL,
    approved BOOLEAN DEFAULT false,
    PRIMARY KEY (id)
) ENGINE=INNODB;
