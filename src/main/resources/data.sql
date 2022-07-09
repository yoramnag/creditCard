insert into BLACK_LIST(id,credit_card) values(10001,'56e7fc920e4283f65412b1668110f5bf9552a697b90928869219158d87b70be7');
insert into BLACK_LIST(id,credit_card) values(10002,'147a30c6e914bffaaaa957d9287226759a27d3eb5d27212c901e5944d565a7dc');
insert into BLACK_LIST(id,credit_card) values(10003,'8ee199cb76811f0338cf70e3973a68590b91536b78736db73a21e4f296b8e9c1');

insert into TRANSACTIONS(id , credit_card , date ,amount,mask_credit_card) values(20001 , '89474490a51a4906a89d5ffc873bfe97d5e695a75c8a8b1ae04b4dc44915f22e',PARSEDATETIME('07-07-2022-00.00.00','dd-MM-yyyy-HH.mm.ss'),100.00,'XXXXXXXXXXXX7797');
insert into TRANSACTIONS(id , credit_card , date ,amount,mask_credit_card) values(20002 , '89474490a51a4906a89d5ffc873bfe97d5e695a75c8a8b1ae04b4dc44915f22e',PARSEDATETIME('08-07-2022-00.00.00','dd-MM-yyyy-HH.mm.ss'),300.00,'XXXXXXXXXXXX7797');