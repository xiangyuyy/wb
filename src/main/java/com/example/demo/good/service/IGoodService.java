package com.example.demo.good.service;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.good.entity.Good;
import com.example.demo.goodclass.entity.Goodclass;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
public interface IGoodService extends IService<Good> {
	PageVo<Good> GetPageList(Good good,PagePara pagePara);
}
