package com.zq.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("t_udict")
public class Udict {

	private Long dictid;

	private String status;

	private String value;
}
