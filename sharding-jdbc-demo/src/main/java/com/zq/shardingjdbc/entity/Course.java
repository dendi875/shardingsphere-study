package com.zq.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 
 * @author <a href="mailto:quanzhang875@gmail.com">quanzhang875</a>
 * @since  2023-11-13 18:41:29
 */
@Data
@Builder
public class Course {

	private Long cid;

	private String name;

	private Long userId;

	private String status;

	@TableField("create_dt")
	private LocalDateTime createTime;
}
