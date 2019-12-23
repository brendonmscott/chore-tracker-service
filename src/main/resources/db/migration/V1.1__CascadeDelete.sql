ALTER TABLE user_family_members
    ADD CONSTRAINT fk_user_family_member
        FOREIGN KEY (family_member_id)
            REFERENCES user(id)
            ON DELETE CASCADE;