use fast_sns;

show tables from fast_sns;

create table Member
(
    id        int auto_increment,
    email     varchar(20) not null,
    nickname  varchar(20) not null,
    birthday  date        not null,
    createdAt datetime    not null,
    constraint member_id_uindex
        primary key (id)
);

select *
from Member;

create table MemberNicknameHistory
(
    id        int auto_increment,
    memberId  int         not null,
    nickname  varchar(20) not null,
    createdAt datetime    not null,
    constraint memberNicknameHistory_id_uindex
        primary key (id)
);

select *
from MemberNicknameHistory;

create table Follow
(
    id           int auto_increment,
    fromMemberId int      not null,
    toMemberId   int      not null,
    createdAt    datetime not null,
    constraint Follow_id_uindex
        primary key (id)
);

select *
from Follow;

create unique index Follow_fromMemberId_toMemberId_uindex
    on Follow (fromMemberId, toMemberId);


create table POST
(
    id          int auto_increment,
    memberId    int          not null,
    contents    varchar(100) not null,
    createdDate date         not null,
    createdAt   datetime     not null,
    constraint POST_id_uindex
        primary key (id)
);

select *
from POST;

select COUNT(*)
from POST;

truncate POST;

-- 검색 쿼리를 어떻게 타는지 확인하는것
explain
SELECT createdDate, memberId, count(id) as count
FROM POST use index (POST__index_member_id_created_date)
WHERE memberId = 4
  and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

-- 데이터 분포도 by memberId
select memberId, count(id)
from POST
group by memberId;

-- 데이터 분포도 by createdDate
select createdDate, count(id)
from POST
group by createdDate
order by 2 desc;

select count(distinct (createdDate))
from POST;


create index POST__index_member_id
    on POST (memberId);

create index POST__index_created_date
    on POST (createdDate);

create index POST__index_member_id_created_date
    on POST (memberId, createdDate);


-- pagination - limit & offset
select *
from POST
where memberId = 1
order by id desc
limit 2 offset 0;

-- pagination
select *
from POST
where memberId = 1
  and id > 1000
order by id asc;

create table Timeline
(
    id        int auto_increment,
    memberId  int      not null,
    postId    int      not null,
    createdAt datetime not null,
    constraint Timeline_id_uindex
        primary key (id)
);

commit;

select *
from Follow;

select *
from POST
where memberId = 1
order by createdAt DESC
limit 10;





