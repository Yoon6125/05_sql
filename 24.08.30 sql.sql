use my_cat;
create table board (
	b_no int primary key auto_increment, 	#글번호
	b_title char(100),						#글제목
    b_id char(20) not null,					#작성자id
	b_datetime datetime not null,			#작성시간
    b_hit int,								#조회수    
    b_text text,							#글내용
    b_reply_count int,						#댓글수
    b_reply_ori int,						#댓글의 원글 번호
    b_reply_text text						#댓글내용
);
drop table board;

#일반글을 썼다고 가정
insert into board (b_title,b_id,b_datetime,b_text,b_hit)
values (
'헬로'
,
'cat1'
,
now()
,
'글입니다. 글글.....글....'
,
0
);

#댓글을 썼다고 가정
insert into board (b_id,b_datetime,b_reply_ori,b_reply_text)
values (
'cat1'
,
now()
,
10
,
'댓글임......욕욕욕...'
);
delete from board;
select * from board;
select * from board where b_no=10;
select * from board where not b_title is null;
select * from board where b_reply_ori=10;

update board set b_no=10 where b_no=20;

insert into board(b_title,b_id,b_datetime,b_text,b_hit) value(
'자유글',
'cat2',
now(),
'글 쓰기 연습',
0
);

#테이블 내 데이터 수 구하기 
select count(*) from board;

update board set b_reply_ori=20 where b_reply_ori=10;
update board set b_reply_ori=40 where b_no=25;
update board set b_id=cat44 where b_no=28;
-- update board set b_no=40 where b_no=25;

delete from board where b_no=24;
delete from board where b_no=25;

show tables;

select * from board;






