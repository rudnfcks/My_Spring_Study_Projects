drop table post;

create table post (
                      id int8 primary key generated always as identity,
                      title varchar(255),
                      content text,
                      write_date char(19),
                      change_date char(19),
                      writer varchar(255)
);

insert into post(title, content, write_date, writer)
values ('테스트 게시물1', '테스트 게시물 내용','2022-01-07 10:19:12','tester');
insert into post(title, content, write_date, writer)
values ('테스트 게시물2', '테스트 게시물 내용','2022-01-07 10:20:15','tester2');

insert into post(title, content, write_date, writer)
values ('테스트 게시물2', '테스트 게시물 내용','2022-01-07 10:20:15','tester2');

select * from post;