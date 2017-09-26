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



/*��ʼ������*/

INSERT INTO staffer VALUES ('0000000000','admin','C11EBE0DAE3AFD6F3B81E435EC6DAAE2C297AC89CA937536EAA1AB3D','00','00','0000000000','0000000000','2010-01-01','00');
insert into staffer values('1000000001','ǰ̨','C11EBE0DAE3AFD6F3B81E435EC6DAAE2C297AC89CA937536EAA1AB3D','01','01','0000000001','0000000000','2017-07-04','00');
insert into staffer values('2000000001','���Ա','C11EBE0DAE3AFD6F3B81E435EC6DAAE2C297AC89CA937536EAA1AB3D','00','00','0000000002','0000000000','2017-07-04','00');


INSERT INTO menu VALUES ('0100000000','��������','0','','00'),                                                                
												('0101000000','ϵͳ�˵�����','1','/AutoRStore/sysadm/menuInit.do','00'),                              
												('0102000000','���Ź���','1','/AutoRStore/sysadm/departmentInit.do','00'),                            
												('0103000000','����Ȩ�޹���','1','/AutoRStore/sysadm/deprightInit.do','00'),                          
												('0104000000','��������','1','/AutoRStore/sysadm/dicDataInit.do','00'),                               
												('0106000000','ְԱ����','1','/AutoRStore/sysadm/stafferInit.do','00'),                               
												('0107000000','��������','1','/AutoRStore/sysadm/branchInit.do','00'),           
												            
												('0200000000','��ѯͳ��ģ��','0','','00'),                                                                
												('0201000000','ְԱͳ��','1','/AutoRStore/chart/stafferPrint.do','00'),                               
												('0202000000','����ͳ��','1','/AutoRStore/chart/branchPrint.do','00'),          
												             
												('0203000000','��Աͳ��','0','','00'),                                                                
												('0203010000','��ʱ���ͳ�ƻ���','1','/AutoRStore/chart/time/associatorInit.do','00'),                
												('0203020000','��Ա�ܱ�','1','/AutoRStore/chart/associatorPrint.do','00'),          
												
												('0204000000','��ˮ��ѯ','0','','00'),
												('0204010000','������ˮ','1','/AutoRStore/autorstore/statistic/consumeHisQry.jsp','00'),
												('0204020000','������ϸͳ��','1','/AutoRStore/chart/consumeListHisQryInit.do','00'),
												('0204030000','��ֵ��ˮ','1','/AutoRStore/autorstore/statistic/rechargeHisQry.jsp','00'),
												('0204040000','��Ա�ײͲ�ѯ','1','/AutoRStore/autorstore/associator/assComboQryInit.jsp','00'),  
												             
												('0300000000','����ģ��','0','','00'),                                                                
												('0301000000','����ǰ̨','1','/AutoRStore/sale/salesInit.do','00'),                 
												                  
												('0400000000','��Ա����ģ��','0','','00'),                                                            
												('0401000000','��Ա����','1','/AutoRStore/sysadm/associatorInit.do','00'),                            
												('0402000000','��Ա����','1','/AutoRStore/sysadm/associatorManageInit.do','00'),                      
												('0403000000','��Ա�������޸�','1','/AutoRStore/autorstore/sysadm/associatorEditPwd.jsp','00'),       
												('0404000000','��Ա��ֵ','1','/AutoRStore/autorstore/sysadm/assRechargeInit.jsp','00'),               
												('0405000000','��Ա�ײ͹���','1','/AutoRStore/autorstore/associator/assComboInit.jsp','00'),        
												
												('0500000000','������Ŀ����ģ��','0','','00'),                                                        
												('0501000000','ά�޲������','1','/AutoRStore/material/addMaterialInit.do','00'),                     
												('0503000000','ά�޲��ϲ�ѯ','1','/AutoRStore/material/queryInit.do','00'),                           
												('0505000000','ά����Ŀ����','1','/AutoRStore/material/repairItemAddInit.do','00'),                   
												('0506000000','ά����Ŀ��ѯ','1','/AutoRStore/material/repairItemList.do','00');                      

INSERT INTO dic_data VALUES ('0000','00','��Ч','״̬'),
														('0000','01','��Ч','״̬'),
														('0001','00','ϵͳ����Ա','��������'),
														('0001','01','������','��������'),
														('0001','02','֧�ֲ���','��������'),
														('0002','0','��','�Ƿ�'),
														('0002','1','��','�Ƿ�'),
														('0003','00','����Ա','Ա����λ'),
														('0003','01','����','Ա����λ'),
														('0003','02','����','Ա����λ'),
														('0004','00','����Ա','Ա������'),
														('0004','01','һ��','Ա������'),
														('0004','02','����','Ա������'),
														('0005','00','�ܵ�','��������'),
														('0005','01','һ����֧����','��������'),
														('0005','02','������֧����','��������'),
														('0006','00','��ͨ��Ա','��Ա����'),
														('0006','01','һ����Ա','��Ա����'),
														('0006','02','������Ա','��Ա����'),
														('0007','00','��','�Ա�'),
														('0007','01','Ů','�Ա�'),
														('0008','00','������','ά�޲���(��Ŀ)����'),
														('0008','01','ά����','ά�޲���(��Ŀ)����'),
														('0009','01','�ֽ�','���ʽ'),
														('0009','02','�ײ�','���ʽ'),
														('0009','03','���п�','���ʽ'),
														('0009','04','֧����','���ʽ'),
														('0009','05','΢��֧��','���ʽ'),
														('0009','06','��Ϸ�ʽ','���ʽ'),
														('0009','07','��Ա�����','���ʽ'),
														('0009','99','����','���ʽ');

INSERT INTO department VALUES ('0000000000','����Ա','00','00'),
															('0000000001','���۹�̨','01','00'),
															('0000000002','��ܲ�','02','00');

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


INSERT INTO branch VALUES ('0000000000','�ܵ�','00','','00');

INSERT INTO associator VALUES 
('0000000000','18888888888','','666666','00','2000-01-01',NULL,0,'00','2000-01-01','2000-01-01','0.00','0.00','','00'),
('1000000000','13232323232','cs','666666','00','2017-05-01',NULL,0,'00','2017-05-24','2017-05-24','0.00','0.00','','00'),
('1000000001','13789861412',NULL,'666666','00','2017-06-01',NULL,0,'00','2017-06-10','2017-06-10','0.00','0.00',NULL,'00'),
('1000000002','13212121211',NULL,'666666','00','2008-06-10',NULL,0,'00','2017-06-10','2017-06-10','0.00','0.00',NULL,'00'),
('1000000003','13232323233','cs','666666','00','2017-05-01',NULL,0,'00','2017-05-24','2017-05-24','0.00','0.00','','00');

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                