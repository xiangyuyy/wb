package com.example.demo.commisionorder.service;

import com.example.demo.commisionorder.entity.Commisionorder;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 佣金变更订单 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
public interface ICommisionorderService extends IService<Commisionorder> {
	PageVo<Commisionorder> GetPageList(Commisionorder commisionorder, PagePara pagePara);
	List<Commisionorder> GetList(Commisionorder commisionorder);
}
