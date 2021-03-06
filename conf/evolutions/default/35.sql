# --- 

# --- !Ups

create table news (
  news_id bigint not null,
  site_id bigint references site on delete cascade,
  title text not null,
  contents text not null,
  release_time timestamp not null,
  updated_time timestamp not null,
  constraint pk_news primary key (news_id)
);

comment on column news.site_id is 'Owner site of this news or null if administrator message.';

create sequence news_seq start with 1000;

create index ix_news01 on news (release_time);

# --- !Downs

drop index ix_news01;

drop table news;

drop sequence news_seq;
