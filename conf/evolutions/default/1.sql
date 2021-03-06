# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table feature (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  image_url                     varchar(255),
  constraint pk_feature primary key (id)
);

create table user (
  email                         varchar(255) not null,
  password_hash                 varchar(255),
  name                          varchar(255),
  admin                         boolean default false not null,
  salt                          varchar(255),
  constraint pk_user primary key (email)
);


# --- !Downs

drop table if exists feature;

drop table if exists user;

