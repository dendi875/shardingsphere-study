### 表结构
CREATE TABLE `t_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `course_0` (
  `cid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '课程名称',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '课程用户ID',
  `status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '状态',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

### 水平分表的示例
通过 cid % 2 来确认分表
sharding_course 库
    course_0 表
    course_1 表

### 水平分库+水平分表的示例
通过 user_id % 2 来确认分库
通过 cid % 2 来确认分表

sharding_course_0 库
    course_0 表
    course_1 表

sharding_course_1 库
    course_0 表
    course_1 表

### 垂直分库分表
sharding_user 库
    t_user 表
sharding_course 库
    course_0 表
    course_1 表
    
### 公共表示例
1. 什么是公共表：存储固定数据的表，表数据很少发生变化，查询时经常需要和它进行关联
2. 怎么做？
    2.1. 在每个数据库都创建出相同结构的公共表
    2.2. 进行配置 spring.shardingsphere.sharding.broadcast-tables=t_udict

sharding_user 库
    t_udict 用户字典表
sharding_course_0 库
    t_udict 用户字典表
sharding_course_1 库
    t_udict 用户字典表