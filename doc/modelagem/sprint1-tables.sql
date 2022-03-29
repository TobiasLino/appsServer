create tablespace t_doodle datafile 'C:\Oracle\tablespaces\t_doodle.dbf' size 325M reuse;
--create tablespace t_doodle_index datafile 'C:\Oracle\tablespaces\t_doodle_index.dbf' size 10M reuse;
create user doodle identified by doodle default tablespace t_doodle;

create user doodle_dba identified by doodle_dba;
grant dba to doodle_dba;

connect doodle_dba/doodle_dba;

-- Calculo Criação de tabelas pelo DBA
declare
    calc_used_bytes number;
    calc_alloc_bytes number;
begin
    dbms_space.create_table_cost(
        tablespace_name => 'T_DOODLE',
        avg_row_size => 80,
        row_count => 200,
        pct_free => 10,
        used_bytes => calc_used_bytes,
        alloc_bytes => calc_alloc_bytes);
    dbms_output.put_line('bytes usados: ' || (calc_used_bytes / 1024) || 'KB');
    dbms_output.put_line('bytes alocados: ' || (calc_alloc_bytes / 1024) || 'KB');
end;
/
select * from sys.dba_data_files;

-- tables
create table doo_app (
    app_id              number(4) constraint PK_DOODLE_APP primary key,
    app_name            varchar2(20) not null,
    app_created_date    date default sysdate
) tablespace T_DOODLE
storage (initial 70K next 35K minextents 1);

--create index doo_app_idx on doo_app(app_name) tablespace T_DOODLE;
create sequence doo_app_sq start with 1 increment by 1 maxvalue 9999;

create table doo_config (
    config_id       number(4) constraint PK_DOODLE_CONFIG primary key,
    config_name     varchar2(30) not null,
    config_value    varchar2(32) not null,
    app_id          number(4) not null constraint FK_DOODLE_CONFIG_APP references doo_app(app_id)
) tablespace T_DOODLE
storage (initial 70K next 30K minextents 1);

create index doo_config_idx on doo_config(app_id) tablespace T_DOODLE;
create sequence doo_config_sq start with 1 increment by 1 maxvalue 9999;

create table doo_container (
    con_id              number(4) constraint PK_DOODLE_CONTAINER primary key,
    con_name            varchar2(30) not null,
    con_port            number(5) not null constraint UK_DOODLE_CONT_PORT unique,
    con_state           varchar2(20) default 'CREATED',
    con_created_date    date default sysdate,
    con_update_date     date,
    app_id              number(4) not null constraint FK_DOODLE_CONT_APP references doo_app(app_id)
)
tablespace T_DOODLE
storage (initial 75K next 35K minextents 1)
;

create index doo_container_idx on doo_container(app_id) tablespace T_DOODLE;
create sequence doo_container_sq start with 1 increment by 1 maxvalue 9999;

create table doo_deploy (
    deploy_id           number(4) constraint PK_DOODLE_DEPLOY primary key,
    deploy_start_date   date default sysdate,
    deploy_finish_date  date,
    deploy_version      number(5,2),
    deploy_state        varchar2(30),
    con_id        number(4) constraint FK_DOODLE_DEPLOY_CONT references doo_container(con_id)
) tablespace T_DOODLE
storage (initial 80K next 40K minextents 1);

create index doo_deploy_idx on doo_deploy(con_id) tablespace T_DOODLE;
create sequence doo_deploy_sq start with 1 increment by 1 maxvalue 9999;


select * from all_all_tables where tablespace_name = 'T_DOODLE';
select * from all_sequences where sequence_name like 'DOO%';
select * from all_indexes where table_name like 'DOO%';

