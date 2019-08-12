package com.example.demo.hopeorder.modelvo;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 160707
 *微博点赞传入infor
 */
@Data
@Accessors(chain = true)
public class WbdianzanInfor {
	private static final long serialVersionUID = 1L;
	private Integer ztype;
	private String expression;
	private String wburl;
    private String mid;
    private BigDecimal allnum;
    private Integer speed;
    private Integer provinceid;
    private String remark;
}
