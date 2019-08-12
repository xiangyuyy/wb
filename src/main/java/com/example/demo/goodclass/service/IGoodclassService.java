package com.example.demo.goodclass.service;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.goodtype.entity.Goodtype;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品分类 (刷粉 点赞 博文转评。。) 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
public interface IGoodclassService extends IService<Goodclass> {

	PageVo<Goodclass> GetPageList(Goodclass goodclass,PagePara pagePara);
}
