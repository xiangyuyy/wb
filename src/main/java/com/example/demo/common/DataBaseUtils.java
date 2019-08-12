package com.example.demo.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.good.entity.Good;
import com.example.demo.good.service.IGoodService;
import com.example.demo.goodclass.entity.Goodclass;
import com.example.demo.goodclass.service.IGoodclassService;
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;
@Service
public class DataBaseUtils {
	@Autowired
	private ConfigBeanValue config;
	@Autowired
	private IGoodtypeService goodtypeService;
	@Autowired
	private IGoodclassService goodclassService;
	/**
	 * 获得商品类型
	 * @return
	 */
	public  List<String> getGoodtypeName() {
		List<String> sign = new ArrayList<String>();
	    EntityWrapper<Goodtype> ew=new EntityWrapper<Goodtype>();
		List<Goodtype> list = goodtypeService.selectList(ew);
		if (list.size()>0) {
			for (Goodtype goodtype : list) {
				sign.add(goodtype.getName());
			}
		}
		return sign;
	}
	/**
	 * 获得商品分类
	 * @return
	 */
	public  List<String> getGoodclassName() {
		List<String> sign = new ArrayList<String>();
	    EntityWrapper<Goodclass> ew=new EntityWrapper<Goodclass>();
		List<Goodclass> list = goodclassService.selectList(ew);
		if (list.size()>0) {
			for (Goodclass goodclass : list) {
				sign.add(goodclass.getName());
			}
		}
		return sign;
	}
	
	/**
	 * 获得商品标签
	 * @return
	 */
	public  String[] getGoodsignName() {
        String reString = null;
		try {
			reString = new String(config.goodSign.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
		String[] list = reString.split("，");
		return list;
	}
	
	/**
	 * 获得展示图片类型
	 * @return
	 */
	public  String[] getShowPhotoType() {
        String reString = null;
		try {
			reString = new String(config.showPhotoType.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
		String[] list = reString.split("，");
		return list;
	}
}
