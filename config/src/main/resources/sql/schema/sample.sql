create table if not exists sample (
   id serial primary key,
   sample_name varchar(255) not null,
   created_at timestamp not null,
   created_by varchar(100) not null,
   modified_at timestamp null,
   modified_by varchar(100) null
);
