package com.zq.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("t_user")
@Data
@Builder
public class User {

	private Long userId;

	private String name;

	private String status;

	@TableField("create_dt")
	private LocalDateTime createTime;

}
