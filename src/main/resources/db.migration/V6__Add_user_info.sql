create table user_info (
userinfoid int8 not null,
username varchar(255),
userlastname varchar(255),
country varchar(255),
aboutme varchar(2048),
logourl varchar(255) DEFAULT 'https://pbs.twimg.com/profile_images/578844000267816960/6cj6d4oZ_400x400.png' NOT NULL,
dateofbirth date DEFAULT '2001-01-01' NOT NULL,
user_id int8 references usr,
primary key (userinfoid)
)