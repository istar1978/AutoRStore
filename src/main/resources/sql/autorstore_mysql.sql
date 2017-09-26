drop table if exists ASSOCIATOR;

drop table if exists ASS_COMBO;

drop table if exists ASS_CONSUME;

drop table if exists ASS_RECHARGE;

drop table if exists BRANCH;

drop table if exists CONSUME_CART;

drop table if exists CONSUME_LIST;

drop table if exists DEPARTMENT;

drop table if exists DEP_MENU;

drop table if exists DIC_DATA;

drop table if exists ITEM_MATE;

drop table if exists MATERIAL;

drop table if exists MENU;

drop table if exists REPAIR_ITEM;

drop table if exists SERIAL_GEN;

drop table if exists STAFFER;

drop table if exists STATIC_2;

drop table if exists STATIC_3;

drop table if exists STATIC_4;

/*==============================================================*/
/* Table: ASSOCIATOR                                            */
/*==============================================================*/
create table ASSOCIATOR
(
   ASS_ID               varchar(10) not null,
   ASS_PHONE            VARCHAR(11) not null comment '唯一',
   ASS_NAME             varchar(30),
   ASS_PASSWORD         VARCHAR(6),
   ASS_GENDER           VARCHAR(2),
   ASS_BIRTHDAY         DATE,
   ASS_ADDR             varchar(100),
   ASS_POINT            INTEGER,
   ASS_LEVEL            VARCHAR(2),
   ASS_CREATE           DATE,
   ASS_ACTIVE           DATE,
   ASS_BALANCE          decimal(10,2) not null,
   ASS_PBALANCE         DECIMAL(10,2) comment '消费时，优先使用余额，余额使用完毕再使用赠送金额',
   ASS_CARNO            varchar(10),
   ASS_STAT             VARCHAR(2) not null,
   primary key (ASS_ID)
);

alter table ASSOCIATOR comment '会员表';

/*==============================================================*/
/* Table: ASS_COMBO                                             */
/*==============================================================*/
create table ASS_COMBO
(
   COM_ID               VARCHAR(10) not null,
   COM_NAME             VARCHAR(100) not null,
   REP_ID               VARCHAR(10) not null,
   ASS_ID               VARCHAR(10) not null,
   COM_SDATE            DATE not null,
   COM_EDATE            DATE not null,
   COM_DATE             DATE not null,
   COM_TIME             INTEGER not null,
   COM_DESC             VARCHAR(200),
   COM_ITEM             VARCHAR(4) not null,
   COM_PRICE            DECIMAL(10,2) not null,
   COM_STAT             VARCHAR(2) not null,
   primary key (COM_ID)
);

/*==============================================================*/
/* Table: ASS_CONSUME                                           */
/*==============================================================*/
create table ASS_CONSUME
(
   CON_ID               VARCHAR(20) not null,
   ASS_ID               VARCHAR(10) not null,
   CON_DATE             VARCHAR(8) not null,
   CON_TIME             VARCHAR(6) not null,
   CON_AMOUNT           decimal(10,2) not null,
   CON_RAMOUNT          DECIMAL(10,2) not null comment '此属性作用：比如消费金额是10.2元，但是实收10元计入此列',
   CON_TYPE             VARCHAR(2) not null,
   CON_POINT            INTEGER not null,
   STA_ID               VARCHAR(10) not null,
   BRA_ID               VARCHAR(10) not null,
   CON_COMBO            VARCHAR(2) not null,
   COM_ID               VARCHAR(10),
   CON_DESC             VARCHAR(200),
   CON_COLLECT          DECIMAL(10,2) not null,
   CON_CHANGE           DECIMAL(10,2) not null,
   primary key (CON_ID)
);

/*==============================================================*/
/* Table: ASS_RECHARGE                                          */
/*==============================================================*/
create table ASS_RECHARGE
(
   REC_ID               VARCHAR(20) not null,
   ASS_ID               VARCHAR(10) not null,
   REC_AMOUNT           decimal(10,2) not null,
   REC_DATE             VARCHAR(8) not null,
   REC_TIME             VARCHAR(6) not null,
   REC_PRESENT          decimal(10,2),
   REC_TEXT             VARCHAR(200),
   primary key (REC_ID)
);

/*==============================================================*/
/* Table: BRANCH                                                */
/*==============================================================*/
create table BRANCH
(
   BRA_ID               VARCHAR(10) not null,
   BRA_NAME             VARCHAR(30) not null,
   BRA_LEVEL            VARCHAR(10),
   BRA_UPID             VARCHAR(10),
   BRA_STAT             VARCHAR(2) not null,
   primary key (BRA_ID)
);

/*==============================================================*/
/* Table: CONSUME_CART                                          */
/*==============================================================*/
create table CONSUME_CART
(
   CART_NO              INTEGER not null auto_increment,
   ASS_ID               VARCHAR(10) not null,
   REP_ID               VARCHAR(10),
   MAT_ID               VARCHAR(10),
   CART_NUM             INTEGER not null,
   CART_PERPRICE        DECIMAL(10,2) not null,
   CART_MONEY           decimal(10,2) not null,
   primary key (CART_NO)
);

alter table CONSUME_CART comment '销售模块使用
用完即清';

/*==============================================================*/
/* Table: CONSUME_LIST                                          */
/*==============================================================*/
create table CONSUME_LIST
(
   LIS_ID               VARCHAR(10) not null,
   CON_ID               VARCHAR(20) not null,
   REP_ID               VARCHAR(10),
   MAT_ID               VARCHAR(10),
   LIS_NUM              INTEGER not null,
   LIS_PRICE            decimal(10,2) not null,
   primary key (LIS_ID)
);

alter table CONSUME_LIST comment '消费项目和维修材料编号二者选一，互斥';

/*==============================================================*/
/* Table: DEPARTMENT                                            */
/*==============================================================*/
create table DEPARTMENT
(
   DEP_ID               VARCHAR(10) not null,
   DEP_NAME             VARCHAR(30),
   DEP_TYPE             VARCHAR(2),
   DEP_STAT             VARCHAR(2),
   primary key (DEP_ID)
);

/*==============================================================*/
/* Table: DEP_MENU                                              */
/*==============================================================*/
create table DEP_MENU
(
   DEP_ID               VARCHAR(10) not null,
   MEN_ID               VARCHAR(10) not null,
   DM_STAT              VARCHAR(2) not null,
   primary key (DEP_ID, MEN_ID)
);

/*==============================================================*/
/* Table: DIC_DATA                                              */
/*==============================================================*/
create table DIC_DATA
(
   DIC_LARGE            VARCHAR(4) not null,
   DIC_VALUE            VARCHAR(2) not null,
   DIC_NAME             VARCHAR(100) not null,
   DIC_TEXT             VARCHAR(200),
   primary key (DIC_LARGE, DIC_VALUE)
);

/*==============================================================*/
/* Table: ITEM_MATE                                             */
/*==============================================================*/
create table ITEM_MATE
(
   REP_ID               VARCHAR(10) not null,
   MAT_ID               VARCHAR(10) not null,
   IM_UNIT              VARCHAR(10),
   IM_NUM               INTEGER,
   primary key (REP_ID, MAT_ID)
);

/*==============================================================*/
/* Table: MATERIAL                                              */
/*==============================================================*/
create table MATERIAL
(
   MAT_ID               VARCHAR(10) not null,
   MAT_NAME             VARCHAR(200) not null,
   MAT_CLASSIFY         VARCHAR(4) not null,
   MAT_PREPRICE         decimal(10,2) not null,
   MAT_REALPRICE        decimal(10,2) not null,
   MAT_FACTORY          VARCHAR(200),
   MAT_PRODATE          DATE,
   MAT_INDATE           DATE not null,
   MAT_NUM              INTEGER not null,
   BRA_ID               VARCHAR(10) not null,
   MAT_STAT             VARCHAR(2) not null,
   primary key (MAT_ID, BRA_ID, MAT_STAT)
);

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
create table MENU
(
   MEN_ID               VARCHAR(10) not null,
   MEN_NAME             VARCHAR(30) not null,
   MEN_ATT              VARCHAR(1) not null,
   MEN_URL              VARCHAR(100),
   MEN_STAT             VARCHAR(2) not null,
   primary key (MEN_ID)
);

/*==============================================================*/
/* Table: REPAIR_ITEM                                           */
/*==============================================================*/
create table REPAIR_ITEM
(
   REP_ID               VARCHAR(10) not null,
   REP_NAME             VARCHAR(100) not null,
   REP_CLASSIFY         VARCHAR(4),
   REP_MONEY            decimal(10,2),
   REP_STAT             VARCHAR(2) not null,
   primary key (REP_ID)
);

alter table REPAIR_ITEM comment '类似于套餐项目，如保养套餐(机油、机滤)';

/*==============================================================*/
/* Table: SERIAL_GEN                                            */
/*==============================================================*/
create table SERIAL_GEN
(
   SER_LARGE            VARCHAR(2) not null,
   SER_SMALL            VARCHAR(2) not null,
   SER_NO               VARCHAR(100) not null,
   SER_RULE             VARCHAR(200),
   primary key (SER_LARGE, SER_SMALL)
);

alter table SERIAL_GEN comment '生成流水号';

/*==============================================================*/
/* Table: STAFFER                                               */
/*==============================================================*/
create table STAFFER
(
   STA_ID               VARCHAR(10) not null,
   STA_NAME             VARCHAR(30),
   STA_PWD              VARCHAR(20),
   STA_POSITION         VARCHAR(2),
   STA_LEVEL            VARCHAR(2),
   DEP_ID               VARCHAR(10) not null,
   BRA_ID               VARCHAR(10) not null,
   STA_CDATE            DATE,
   STA_STAT             VARCHAR(2) not null,
   primary key (STA_ID)
);

/*==============================================================*/
/* Table: STATIC_2                                              */
/*==============================================================*/
create table STATIC_2
(
   ASS_ID               VARCHAR(10) not null,
   S2_POINT             INTEGER not null,
   primary key (ASS_ID)
);

alter table STATIC_2 comment '统计一段时间内，某机构会员积分的列表
用完一次就立即清理';

/*==============================================================*/
/* Table: STATIC_3                                              */
/*==============================================================*/
create table STATIC_3
(
   MAT_ID               VARCHAR(10) not null,
   MAT_NAME             VARCHAR(200) not null,
   MAT_REALPRICE        decimal(10,2) not null comment '实际上架的单价',
   MAT_PREPRICE         decimal(10,2) not null,
   S3_NUM               integer not null,
   S3_SUMPRICE          decimal(12,2) not null,
   S3_RSUMPRICE         decimal(12,2) not null,
   primary key (MAT_ID)
);

alter table STATIC_3 comment '统计维修材料销售情况的时候使用，用完即清';

/*==============================================================*/
/* Table: STATIC_4                                              */
/*==============================================================*/
create table STATIC_4
(
   S4_DATE              date not null,
   BRA_ID               VARCHAR(10) not null,
   BRA_NAME             VARCHAR(30) not null,
   S4_ALLPRICE          decimal(12,2) not null,
   S4_REALPRICE         decimal(12,2) not null,
   primary key (S4_DATE, BRA_ID)
);

alter table STATIC_4 comment '统计机构利润时使用，用完即清';
