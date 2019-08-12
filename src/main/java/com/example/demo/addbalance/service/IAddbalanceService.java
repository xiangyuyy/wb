package com.example.demo.addbalance.service;

import com.example.demo.addbalance.entity.Addbalance;
import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.hopeorder.entity.Hopeorder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 充值订单 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-04-07
 */
public interface IAddbalanceService extends IService<Addbalance> {
	PageVo<Addbalance> GetPageList(Addbalance addbalance, PagePara pagePara);
}
