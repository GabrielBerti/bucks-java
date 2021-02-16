
select * from titulo ;

select * from conta;

update conta set vl_saldo = 0;
update titulo set dt_pgto = null;
delete from titulo_extrato ;
commit;

select * from grupo_titulo ;
delete from titulo ;

select * from titulo ;

delete from titulo ;
delete from conta ;
delete from grupo_titulo  ;
delete from tipo_pagamento  ;
delete from titulo ;


  CREATE SEQUENCE public.conta_seq --mudar
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 999 -- mudar
  START 1
  CACHE 1;

  CREATE SEQUENCE public.grupo_titulo_seq --mudar
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 999 -- mudar
  START 1
  CACHE 1;

  CREATE SEQUENCE public.tipo_pagamento_seq --mudar
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 999 -- mudar
  START 1
  CACHE 1;

  CREATE SEQUENCE public.titulo_seq --mudar
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 999999 -- mudar
  START 1
  CACHE 1;

  CREATE SEQUENCE public.titulo_extrato_seq --mudar
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 999999 -- mudar
  START 1
  CACHE 1;



  CREATE TABLE titulo_extrato
(
  num integer NOT NULL,
  fk_titulo_num integer NOT NULL,
  vl numeric(18,2) NOT NULL,
  vl_saldo_ant numeric(18,2) NOT NULL,
  vl_saldo_atu numeric(18,2) NOT NULL,
  us_cad character varying(30) NOT NULL,
  dt_cad date NOT NULL,
  us_alt character varying(30),
  dt_alt date,
  obs character varying(200),
  CONSTRAINT pk_titulo_extrato PRIMARY KEY (fk_titulo_num)
)
WITH (
  OIDS=FALSE
);