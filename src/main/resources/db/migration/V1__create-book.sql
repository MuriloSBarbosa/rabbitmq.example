create table book (
  id VARCHAR(36) primary key,
  title varchar(255),
  genre varchar(255),
  director varchar(255),
  release_date timestamp,
  rating decimal(2,1),
  description varchar(255)
);