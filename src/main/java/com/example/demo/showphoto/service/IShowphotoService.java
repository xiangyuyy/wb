package com.example.demo.showphoto.service;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.good.entity.Good;
import com.example.demo.showphoto.entity.Showphoto;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 展示页面图片 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
public interface IShowphotoService extends IService<Showphoto> {
	PageVo<Showphoto> GetPageList(Showphoto showphoto,PagePara pagePara);
}
