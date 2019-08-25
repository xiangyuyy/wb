package com.example.demo.good.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;
import com.example.demo.utils.excel.ExcelColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author chenxiangyu
 * @since 2019-01-25
 */
@Data
@Accessors(chain = true)
public class GoodExcel implements Serializable{

    private static final long serialVersionUID = 1L;

    @ExcelColumn(value = "id", columnName ="商品id", col = 1)
    private Integer id;
    @ExcelColumn(value = "goodtypename", columnName ="商品类型名称", col = 2)
    private String goodtypename;
    @ExcelColumn(value = "rank", columnName ="商品排位", col = 3)
    private Integer rank;
    @ExcelColumn(value = "createtime", columnName ="商品创建时间", col = 4)
    private Date createtime;
}
