create table if not exists insert_lock (
  id int(10) unsigned not null auto_increment,
  method varchar(128) not null comment '方法名',
  ip_thread_id varchar(256) not null comment '标识某个线程',
  create_time timestamp not null default current_timestamp comment '创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
  primary key (id),
  constraint unique(method), unique(ip_thread_id)
);

insert into insert_lock(method, ip_thread_id) values('addUser', '10.123.12.23_3244');
update insert_lock set method = 'updateUser' where method = 'addUser';