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
   ASS_PHONE            VARCHAR(11) not null ,
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
   ASS_PBALANCE         DECIMAL(10,2) ,
   ASS_CARNO            varchar(10),
   ASS_STAT             VARCHAR(2) not null,
   primary key (ASS_ID)
);


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
   CON_RAMOUNT          DECIMAL(10,2) not null ,
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
   STA_ID               VARCHAR(10) not null,
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


/*==============================================================*/
/* Table: STAFFER                                               */
/*==============================================================*/
create table STAFFER
(
   STA_ID               VARCHAR(10) not null,
   STA_NAME             VARCHAR(30),
   STA_PWD              VARCHAR(200),
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


/*==============================================================*/
/* Table: STATIC_3                                              */
/*==============================================================*/
create table STATIC_3
(
   MAT_ID               VARCHAR(10) not null,
   MAT_NAME             VARCHAR(200) not null,
   MAT_REALPRICE        decimal(10,2) not null ,
   MAT_PREPRICE         decimal(10,2) not null,
   S3_NUM               integer not null,
   S3_SUMPRICE          decimal(12,2) not null,
   S3_RSUMPRICE         decimal(12,2) not null,
   primary key (MAT_ID)
);


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



/*初始化数据*/

INSERT INTO staffer VALUES ('0000000000','admin','C11EBE0DAE3AFD6F3B81E435EC6DAAE2C297AC89CA937536EAA1AB3D','00','00','0000000000','0000000000','2010-01-01','00');
insert into staffer values('1000000001','前台','C11EBE0DAE3AFD6F3B81E435EC6DAAE2C297AC89CA937536EAA1AB3D','01','01','0000000001','0000000000','2017-07-04','00');
insert into staffer values('2000000001','库管员','C11EBE0DAE3AFD6F3B81E435EC6DAAE2C297AC89CA937536EAA1AB3D','00','00','0000000002','0000000000','2017-07-04','00');


INSERT INTO menu VALUES ('0100000000','参数定义','0','','00'),                                                                
												('0101000000','系统菜单管理','1','/AutoRStore/sysadm/menuInit.do','00'),                              
												('0102000000','部门管理','1','/AutoRStore/sysadm/departmentInit.do','00'),                            
												('0103000000','部门权限管理','1','/AutoRStore/sysadm/deprightInit.do','00'),                          
												('0104000000','参数管理','1','/AutoRStore/sysadm/dicDataInit.do','00'),                               
												('0106000000','职员管理','1','/AutoRStore/sysadm/stafferInit.do','00'),                               
												('0107000000','机构管理','1','/AutoRStore/sysadm/branchInit.do','00'),           
												            
												('0200000000','查询统计模块','0','','00'),                                                                
												('0201000000','职员统计','1','/AutoRStore/chart/stafferPrint.do','00'),                               
												('0202000000','机构统计','1','/AutoRStore/chart/branchPrint.do','00'),          
												             
												('0203000000','会员统计','0','','00'),                                                                
												('0203010000','按时间段统计积分','1','/AutoRStore/chart/time/associatorInit.do','00'),                
												('0203020000','会员总表','1','/AutoRStore/chart/associatorPrint.do','00'),          
												
												('0204000000','流水查询','0','','00'),
												('0204010000','销售流水','1','/AutoRStore/autorstore/statistic/consumeHisQry.jsp','00'),
												('0204020000','销售明细统计','1','/AutoRStore/chart/consumeListHisQryInit.do','00'),
												('0204030000','充值流水','1','/AutoRStore/autorstore/statistic/rechargeHisQry.jsp','00'),
												('0204040000','会员套餐查询','1','/AutoRStore/autorstore/associator/assComboQryInit.jsp','00'),  
												             
												('0300000000','销售模块','0','','00'),                                                                
												('0301000000','销售前台','1','/AutoRStore/sale/salesInit.do','00'),                 
												                  
												('0400000000','会员管理模块','0','','00'),                                                            
												('0401000000','会员激活','1','/AutoRStore/sysadm/associatorInit.do','00'),                            
												('0402000000','会员定义','1','/AutoRStore/sysadm/associatorManageInit.do','00'),                      
												('0403000000','会员卡密码修改','1','/AutoRStore/autorstore/sysadm/associatorEditPwd.jsp','00'),       
												('0404000000','会员充值','1','/AutoRStore/autorstore/sysadm/assRechargeInit.jsp','00'),               
												('0405000000','会员套餐购买','1','/AutoRStore/autorstore/associator/assComboInit.jsp','00'),        
												
												('0500000000','材料项目管理模块','0','','00'),                                                        
												('0501000000','维修材料入库','1','/AutoRStore/material/addMaterialInit.do','00'),                     
												('0503000000','维修材料查询','1','/AutoRStore/material/queryInit.do','00'),                           
												('0505000000','维修项目定义','1','/AutoRStore/material/repairItemAddInit.do','00'),                   
												('0506000000','维修项目查询','1','/AutoRStore/material/repairItemList.do','00');                      

INSERT INTO dic_data VALUES ('0000','00','有效','状态'),
														('0000','01','无效','状态'),
														('0001','00','系统管理员','部门类型'),
														('0001','01','销售类','部门类型'),
														('0001','02','支持部门','部门类型'),
														('0002','0','是','是否'),
														('0002','1','否','是否'),
														('0003','00','管理员','员工岗位'),
														('0003','01','销售','员工岗位'),
														('0003','02','主任','员工岗位'),
														('0004','00','管理员','员工级别'),
														('0004','01','一级','员工级别'),
														('0004','02','二级','员工级别'),
														('0005','00','总店','机构级别'),
														('0005','01','一级分支机构','机构级别'),
														('0005','02','二级分支机构','机构级别'),
														('0006','00','普通会员','会员级别'),
														('0006','01','一级会员','会员级别'),
														('0006','02','二级会员','会员级别'),
														('0007','00','男','性别'),
														('0007','01','女','性别'),
														('0008','00','保养类','维修材料(项目)分类'),
														('0008','01','维修类','维修材料(项目)分类'),
														('0009','01','现金','付款方式'),
														('0009','02','套餐','付款方式'),
														('0009','03','银行卡','付款方式'),
														('0009','04','支付宝','付款方式'),
														('0009','05','微信支付','付款方式'),
														('0009','06','组合方式','付款方式'),
														('0009','07','会员卡余额','付款方式'),
														('0009','99','其他','付款方式');

INSERT INTO department VALUES ('0000000000','管理员','00','00'),
															('0000000001','销售柜台','01','00'),
															('0000000002','库管部','02','00');

INSERT INTO dep_menu VALUES ('0000000000','0101000000','00'),
														('0000000000','0102000000','00'),
														('0000000000','0103000000','00'),
														('0000000000','0104000000','00'),
														('0000000000','0106000000','00'),
														('0000000000','0107000000','00'),
														('0000000000','0201000000','00'),
														('0000000000','0202000000','00'),
														('0000000000','0203020000','00'),
														('0000000000','0204010000','00'),
														('0000000000','0204020000','00'),
														('0000000000','0204030000','00'),
														('0000000000','0204040000','00'),
														('0000000000','0301000000','00'),
														('0000000000','0401000000','00'),
														('0000000000','0402000000','00'),
														('0000000000','0403000000','00'),
														('0000000000','0404000000','00'),
														('0000000000','0405000000','00'),
														('0000000000','0501000000','00'),
														('0000000000','0503000000','00'),
														('0000000000','0505000000','00'),
														('0000000000','0506000000','00'),
														('0000000001','0201000000','00'),
														('0000000001','0202000000','00'),
														('0000000001','0203020000','00'),
														('0000000001','0204010000','00'),
														('0000000001','0204020000','00'),
														('0000000001','0204030000','00'),
														('0000000001','0204040000','00'),
														('0000000001','0301000000','00'),
														('0000000001','0401000000','00'),
														('0000000001','0402000000','00'),
														('0000000001','0403000000','00'),
														('0000000001','0404000000','00'),
														('0000000001','0405000000','00'),
														('0000000002','0501000000','00'),
														('0000000002','0503000000','00'),
														('0000000002','0505000000','00'),
														('0000000002','0506000000','00');


INSERT INTO branch VALUES ('0000000000','总店','00','','00');

INSERT INTO associator VALUES 
('0000000000','18888888888','','666666','00','2000-01-01',NULL,0,'00','2000-01-01','2000-01-01','0.00','0.00','','00'),
('1000000000','13232323232','cs','666666','00','2017-05-01',NULL,0,'00','2017-05-24','2017-05-24','0.00','0.00','','00'),
('1000000001','13789861412',NULL,'666666','00','2017-06-01',NULL,0,'00','2017-06-10','2017-06-10','0.00','0.00',NULL,'00'),
('1000000002','13212121211',NULL,'666666','00','2008-06-10',NULL,0,'00','2017-06-10','2017-06-10','0.00','0.00',NULL,'00'),
('1000000003','13232323233','cs','666666','00','2017-05-01',NULL,0,'00','2017-05-24','2017-05-24','0.00','0.00','','00');

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                