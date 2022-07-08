insert into BLACK_LIST(id,credit_card) values(10001,'5275320699756705');
insert into BLACK_LIST(id,credit_card) values(10002,'5526667401765870');
insert into BLACK_LIST(id,credit_card) values(10003,'5377240307952792');

insert into TRANSACTIONS(id , credit_card , date ,amount) values(20001 , '5394133851797797',PARSEDATETIME('07-07-2022-00.00.00','dd-MM-yyyy-HH.mm.ss'),100.00);
insert into TRANSACTIONS(id , credit_card , date ,amount) values(20002 , '5394133851797797',PARSEDATETIME('08-07-2022-00.00.00','dd-MM-yyyy-HH.mm.ss'),300.00);