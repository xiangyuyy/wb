package com.example.demo.cshowvo;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.good.entity.Good;
import com.example.demo.goodclass.entity.Goodclass;

import lombok.Data;
import lombok.experimental.Accessors;
/*导航展示下单中心model*/
@Data
@Accessors(chain = true)
public class GoodClassVo {
	private static final long serialVersionUID = 1L;
	private String goodclassname;
	private List<Good> goods;
	private Integer rank;
	
}
