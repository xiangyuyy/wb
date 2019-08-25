package com.example.demo.balancerecord.mapper;

import com.example.demo.balancerecord.entity.Balancerecord;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 余额变更记录 Mapper 接口
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-02-23
 */
public interface BalancerecordMapper extends BaseMapper<Balancerecord> {
	
	@Select("Select * from balancerecord where status=1")
	
	List<Balancerecord> getTestList();
	@Select("Select count(*) from balancerecord where status=1")
	Integer getCount();
}
