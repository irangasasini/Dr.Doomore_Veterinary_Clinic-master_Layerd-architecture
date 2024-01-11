drop database if exists doomore;
create database if not exists doomore ;

use doomore;

create table employees(
  e_id varchar (20)PRIMARY KEY,
  name varchar (20),
  address varchar (50),
  tel_number int(20)
);

create table customer(
  c_id varchar (20)PRIMARY KEY,
  email varchar(50),
  tel_number int(20),
  e_id varchar(20),
 CONSTRAINT  FOREIGN KEY(e_id)REFERENCES employees(e_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table pet(
  p_id varchar(20)PRIMARY KEY,
  name varchar(20),
  age int(5),
  breed varchar(10),
  c_id varchar(20),
 CONSTRAINT  FOREIGN KEY(c_id)REFERENCES customer(c_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table salary(
 s_id varchar(10)PRIMARY KEY,
 date Date,
 time Time,
 e_id varchar (20),
CONSTRAINT FOREIGN KEY(e_id)REFERENCES employees(e_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table veterinary(
 v_id varchar(20)PRIMARY KEY,
 address varchar(20),
 name varchar(10),
 tel_number int(12)
);

create table appointment(
 a_id varchar(10)PRIMARY KEY,
 appointment_status varchar(20),
 date Date,
 p_id varchar(20),
  v_id varchar(20),
 CONSTRAINT FOREIGN KEY(p_id)REFERENCES pet(p_id)ON DELETE CASCADE ON UPDATE CASCADE,
 CONSTRAINT FOREIGN KEY(v_id)REFERENCES veterinary(v_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table schedule(
 sh_id varchar(20)PRIMARY KEY,
 date Date,
 time TIME,
 v_id varchar(20),
CONSTRAINT FOREIGN KEY(v_id)REFERENCES veterinary(v_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table medicine(
 m_code varchar(20)PRIMARY KEY,
 description varchar(20),
 unitPrice varchar(10),
 qty int(15),
 a_id varchar(10),
CONSTRAINT FOREIGN KEY(a_id)REFERENCES appointment(a_id)ON DELETE CASCADE ON UPDATE CASCADE


);

create table payment(
 p_id varchar(10)PRIMARY KEY,
 time Time,
 date Date,
 amount varchar(10),
 a_id varchar(10),
 CONSTRAINT FOREIGN KEY(a_id)REFERENCES appointment(a_id)ON DELETE CASCADE ON UPDATE CASCADE

);

create table user(
 u_id varchar(10)PRIMARY KEY,
 username varchar(20)not null,
 position varchar(10),
 password  varchar(20)
);

INSERT INTO user VALUE("U001","sasini", "Owner" ,"123");

create table orders(
o_id varchar(10)PRIMARY KEY,
date Date,
 type varchar(3)
);

create table suppliers(
 sup_id varchar(10)PRIMARY KEY,
 email varchar(20),
 address varchar(20),
 tel_number varchar(10),
 e_id varchar(20),
 CONSTRAINT FOREIGN KEY(e_id)REFERENCES employees(e_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table items(
 i_id varchar(10)PRIMARY KEY,
 qty int(20),
 description varchar(20),
 o_id varchar(10),
CONSTRAINT FOREIGN KEY(o_id)REFERENCES orders(o_id)ON DELETE CASCADE ON UPDATE CASCADE

);

create table medicine_detail(
 m_code varchar(10),
a_id varchar(10),
qty int,
CONSTRAINT FOREIGN KEY(m_code)REFERENCES medicine(m_code)ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY(a_id)REFERENCES appointment(a_id)ON DELETE CASCADE ON UPDATE CASCADE
);

create table order_detail(
 o_id varchar(10),
i_id varchar(10),
CONSTRAINT FOREIGN KEY(i_id)REFERENCES items(i_id)ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FOREIGN KEY(i_id)REFERENCES items(i_id)ON DELETE CASCADE ON UPDATE CASCADE
);


