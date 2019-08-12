package com.example.demo.goodtype.service;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.goodtype.entity.Goodtype;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品类型 微博 抖音 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
public interface IGoodtypeService extends IService<Goodtype> {

	PageVo<Goodtype> GetPageList(Goodtype goodtype,PagePara pagePara);
}
