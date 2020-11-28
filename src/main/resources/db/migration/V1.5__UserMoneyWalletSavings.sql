alter table user
    change money_earned savings decimal(19,2) default 0.0;

alter table user
    add wallet decimal(19,2) default 0.0;

alter table chore alter column monetary_value set default 0.0;
