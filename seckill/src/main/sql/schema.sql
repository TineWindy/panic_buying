-- initial database

CREATE DATABASE seckill;
USE seckill;

CREATE TABLE seckill(
seckill_id bigint NOT NULL AUTO_INCREMENT,
name varchar(120) NOT NULL,
number int NOT NULL COMMENT '库存',
start_time timestamp NOT NULL,
end_time timestamp NOT NULL,
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

INSERT INTO seckill(name,number,start_time,end_time)
VALUES
  ('阿里offer200一天',500,'2019-03-30 00:00:00','2019-03-31 00:00:00'),
  ('tx offer 300一天',3000,'2019-03-30 00:00:00','2019-03-31 00:00:00');

CREATE TABLE success_killed(
seckill_id bigint NOT NULL,
user_phone bigint NOT NULL,
state tinyint NOT NULL DEFAULT -1 COMMENT '状态；-1，无效；0，成功；1，已付款',
create_time timestamp NOT NULL,
PRIMARY KEY(seckill_id,user_phone),
key id_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;