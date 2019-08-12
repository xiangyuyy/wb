package com.example.demo.customer.modelvo;

//会员等级 0普通 1黄金 2铂金 3钻石 4至尊
public enum CustomerLevelEnum {
	NORMAL(0, "普通"), GOLD(1, "黄金中"), PLATINUM(2, "铂金"), DIAMONDS(3, "钻石")
	, SUPER(4, "至尊");
  private Integer value;
  private String desc;

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

  private CustomerLevelEnum(Integer value, String desc) {
      this.value = value;
      this.desc = desc;
  }
}
