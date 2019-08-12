package com.example.demo.hopeorder.modelvo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *顶点时间计划传输模型基础类
 */
@Data
@Accessors(chain = true)
public class ConfigVo {
	private static final long serialVersionUID = 1L;

	private String startdate ;
	private Integer perhour;
	private Integer pernum;	
}