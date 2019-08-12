package com.example.demo.hopeorder.modelvo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *顶点微博点赞获取订单json参数化
 */
@Data
@Accessors(chain = true)
public class XDWbdianzanCanshu {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String type;
	private String id;
	private String ourl;	
	private String onum;	
	private String stopUrl;	
}
