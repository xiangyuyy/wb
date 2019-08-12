package com.example.demo.hopeorder.modelvo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *顶点参数传输模型基础类
 */
@Data
@Accessors(chain = true)
public class BaseVo {
	private static final long serialVersionUID = 1L;

	private String token;
	private String type;
	private String addtype;
	private Integer proid;
}
