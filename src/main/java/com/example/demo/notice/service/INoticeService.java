package com.example.demo.notice.service;

import com.example.demo.common.PagePara;
import com.example.demo.common.PageVo;
import com.example.demo.notice.entity.Notice;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 最新公告 服务类
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-30
 */
public interface INoticeService extends IService<Notice> {
	PageVo<Notice> GetPageList(Notice notice,PagePara pagePara);
}
