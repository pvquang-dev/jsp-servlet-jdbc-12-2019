package com.laptrinhjavaweb.enums;

public enum BuildingTypeEnum {
  TANG_TRET("tầng trệt"), 
  NGUYEN_CAN("nguyên căn"),
  NOI_THAT("nội thất");

  private final String value;

  private BuildingTypeEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
