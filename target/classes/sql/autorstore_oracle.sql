
create table "Associator"  (
   "id"                 NUMBER(6)                       not null,
   "no"                 CHAR(10)                        not null,
   "name"               CHAR(30),
   "password"           CHAR(6)                         not null,
   "gender"             CHAR(1),
   "birthday"           DATE,
   "addr"               CHAR(100),
   "tel"                CHAR(30),
   "val"                INTEGER                         not null,
   "level"              INTEGER                         not null,
   "create_date"        DATE                            not null,
   "stat"               CHAR(1)                         not null,
   constraint PK_ASSOCIATOR primary key ("id")
);

create table "Branch"  (
   "id"                 CHAR(6)                         not null,
   "name"               CHAR(100)                       not null,
   "level"              CHAR(1)                         not null,
   "stat"               CHAR(1)                         not null,
   constraint PK_BRANCH primary key ("id")
);

create table "Dep_right"  (
   "d_id"               CHAR(8)                         not null,
   "m_id"               CHAR(6)                         not null,
   constraint PK_DEP_RIGHT primary key ("d_id", "m_id")
);

create table "Department"  (
   "id"                 CHAR(8)                         not null,
   "name"               CHAR(30)                        not null,
   "branch_id"          CHAR(6)                         not null,
   "state"              CHAR(1)                         not null,
   constraint PK_DEPARTMENT primary key ("id")
);


create table "Drug"  (
   "id"                 CHAR(10)                        not null,
   "name"               CHAR(100)                       not null,
   "category"           CHAR(8)                         not null,
   "val"                INTEGER                         not null,
   "pre_price"          NUMBER(10,2)                    not null,
   "real_price"         NUMBER(10,2)                    not null,
   "factory"            CHAR(100)                       not null,
   "pro_date"           DATE                            not null,
   "in_date"            DATE                            not null,
   "num"                INTEGER                         not null,
   "stat"               CHAR(1)                         not null,
   constraint PK_DRUG primary key ("id")
);
create table "Menu"  (
   "id"                 CHAR(6)                         not null,
   "name"               CHAR(30)                        not null,
   "att"                CHAR(1)                         not null,
   "url_addr"           CHAR(80)                        not null,
   constraint PK_MENU primary key ("id")
);

create table "Staffer"  (
   "id"                 NUMBER(6)                       not null,
   "name"               CHAR(30)                        not null,
   "password"           CHAR(20)                        not null,
   "position"           CHAR(20)                        not null,
   "level"              CHAR(1)                         not null,
   "dep_id"             CHAR(8)                         not null,
   "create_date"        DATE                            not null,
   "stat"               CHAR(1)                         not null,
   constraint PK_STAFFER primary key ("id")
);