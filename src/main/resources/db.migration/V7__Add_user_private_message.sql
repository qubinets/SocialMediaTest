create table user_private_message (
private_message_id int8 not null,
message_subject varchar(255) not null,
message_body varchar(2048) not null,
message_from int8 references usr not null,
message_to int8 references usr not null,
primary key (private_message_id)
)