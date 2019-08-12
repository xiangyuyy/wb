package com.example.demo.hopeorder.modelvo;

import java.util.stream.Stream;

//交易状态（-1创建失败，0 审核中,1 队列中,2 执行中,3 有异常,5 已暂停,7 今天已完成,8 退款中,9 已完毕,10 已退款）
public enum OrderStatusEunm {
	FAILED(-1, "创建失败"), CHECKED(0, "审核中"), WAIT(1, "队列中"), RUN(2, "执行中")
	, EXCEPT(3, "有异常"), STOP(5, "已暂停"), TODAYF(7, "今天已完成"), RETURN(8, "退款中"), OVER(9, "已完毕"), RETURNF(10, "已退款");

    private Integer value;
    private String desc;

    public static OrderStatusEunm getByType(Integer value) {
        return Stream.of(OrderStatusEunm.values()).filter(item -> item.getValue().intValue() == value.intValue()).findFirst().orElse(FAILED);
    }
    public Integer getValue() {
        return value;
    }

    public void Integer(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }

    private OrderStatusEunm(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
