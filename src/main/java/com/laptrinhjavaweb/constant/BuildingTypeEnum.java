package com.laptrinhjavaweb.constant;

public enum BuildingTypeEnum {
  TANG_TRET("tầng trệt"), 
  NGUYEN_CAN("nguyên căn"), 
  NOI_THAT("nội thất");

  private final String name;

  private BuildingTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
