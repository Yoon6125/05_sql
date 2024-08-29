use my_cat;

create table visit_count(	#테이블 만들기. 칼럼(또는 필드 또는 열이름)은 딸랑 한개.
	count int
);
drop table visit_count;
show tables; 
  
select * from visit_count;
  
insert into visit_count values(0);	#데이터를 한 줄 넣기

update visit_count set count=count+1;

delete from visit_count;

drop table board_guest;
create table board_guest(
	no int primary key auto_increment,
    con char(255),
    writer char(50)
);
insert into board_guest (con,writer) values ('안녕','안');
insert into board_guest (con,writer) values ('하이','가');
insert into board_guest (con,writer) values ('바이','나');
select * from board_guest;

drop table member;
select * from member;
create table member(
	no_member int primary key auto_increment,
    id_member char(8) not null,
    name_member char(10) not null,
    age_member int not null,
    gender_member char(3) not null,
    phone_member char(50) not null
    );
    
    insert into member (id_member , name_member , age_member, gender_member,phone_member) values('ㅁㅁㅁ','가','20','남','010-000-000'); 