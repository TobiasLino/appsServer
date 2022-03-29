create table if not exists doo_app (
                                       app_id              number(4) constraint PK_DOODLE_APP primary key,
                                       app_name            varchar2(20) not null,
                                       app_created_date    date default sysdate
);

--create index doo_app_idx on doo_app(app_name) tablespace T_DOODLE;
create sequence if not exists doo_app_sq start with 1 increment by 1 maxvalue 9999;

create table if not exists doo_config (
                                          config_id       number(4) constraint PK_DOODLE_CONFIG primary key,
                                          config_name     varchar2(30) not null,
                                          config_value    varchar2(32) not null,
                                          app_id          number(4) not null constraint FK_DOODLE_CONFIG_APP references doo_app(app_id)
);

--create index doo_config_idx on doo_config(app_id) tablespace T_DOODLE;
create sequence if not exists doo_config_sq start with 1 increment by 1 maxvalue 9999;

create table if not exists doo_container (
                                             con_id              number(4) constraint PK_DOODLE_CONTAINER primary key,
                                             con_name            varchar2(30) not null,
                                             con_port            number(5) not null constraint UK_DOODLE_CONT_PORT unique,
                                             con_state           varchar2(20) default 'CREATED',
                                             con_created_date    date default sysdate,
                                             con_update_date     date,
                                             app_id              number(4) not null constraint FK_DOODLE_CONT_APP references doo_app(app_id)
);

--create index doo_container_idx on doo_container(app_id) tablespace T_DOODLE;
create sequence if not exists doo_container_sq start with 1 increment by 1 maxvalue 9999;

create table if not exists doo_deploy (
                                          deploy_id           number(4) constraint PK_DOODLE_DEPLOY primary key,
                                          deploy_start_date   date default sysdate,
                                          deploy_finish_date  date,
                                          deploy_version      number(5,2),
                                          deploy_state        varchar2(30),
                                          con_id        number(4) constraint FK_DOODLE_DEPLOY_CONT references doo_container(con_id)
);

--create index doo_deploy_idx on doo_deploy(con_id) tablespace T_DOODLE;
create sequence if not exists doo_deploy_sq start with 1 increment by 1 maxvalue 9999;

insert into doo_app (app_id, app_name) values (1, 'app1');
insert into doo_app (app_id, app_name) values (2, 'app2');

insert into doo_config values (doo_config_sq.nextval, 'BANCO', 'teste', 1);
insert into doo_config values (doo_config_sq.nextval, 'FRONT', 'teste', 1);
insert into doo_config values (doo_config_sq.nextval, 'USER_ID', 'teste', 1);
insert into doo_config values (doo_config_sq.nextval, 'LOGIN_SESSIONS', 'teste', 1);

insert into doo_config values (doo_config_sq.nextval, 'BANCO2', 'teste', 2);
insert into doo_config values (doo_config_sq.nextval, 'FRONT2', 'teste', 2);
insert into doo_config values (doo_config_sq.nextval, 'USER_ID2', 'teste', 2);
insert into doo_config values (doo_config_sq.nextval, 'LOGIN_SESSIONS2', 'teste', 2);