ALTER TABLE chore
    DROP COLUMN reward_id;

ALTER TABLE reward
    DROP COLUMN reward_type;

ALTER TABLE reward
    ADD COLUMN owner_id INTEGER;

CREATE TABLE IF NOT EXISTS reward_assignees (
    id INTEGER AUTO_INCREMENT,
    reward_id INTEGER,
    assignee_id INTEGER,
    PRIMARY KEY (id)
) ENGINE=INNODB;