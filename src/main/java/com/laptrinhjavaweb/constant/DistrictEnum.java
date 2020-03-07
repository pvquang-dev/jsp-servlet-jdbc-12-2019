package com.laptrinhjavaweb.constant;

public enum DistrictEnum {
  QUAN_1("quận 1"), 
  QUAN_2("quận 2"), 
  QUAN_3("quận 3"), 
  QUAN_4("quận 4");

  private final String name;

  private DistrictEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
