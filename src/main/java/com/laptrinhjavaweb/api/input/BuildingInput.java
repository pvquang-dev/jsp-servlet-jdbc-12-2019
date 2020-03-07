package com.laptrinhjavaweb.api.input;

public class BuildingInput {
  private String name;
  private String dictrict;
  private String numberofbasement;
  private String floorArea;
  private String rentAreaFrom;
  private String rentAreaTo;
  private String[] types = new String[] {};
  private String rentCostFrom;
  private String rentCostTo;
  private Long staffId;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDictrict() {
    return dictrict;
  }
  public void setDictrict(String dictrict) {
    this.dictrict = dictrict;
  }
  public String getNumberofbasement() {
    return numberofbasement;
  }
  public void setNumberofbasement(String numberofbasement) {
    this.numberofbasement = numberofbasement;
  }
  public String getFloorArea() {
    return floorArea;
  }
  public void setFloorArea(String floorArea) {
    this.floorArea = floorArea;
  }
  public String getRentAreaFrom() {
    return rentAreaFrom;
  }
  public void setRentAreaFrom(String rentAreaFrom) {
    this.rentAreaFrom = rentAreaFrom;
  }
  public String getRentAreaTo() {
    return rentAreaTo;
  }
  public void setRentAreaTo(String rentAreaTo) {
    this.rentAreaTo = rentAreaTo;
  }
  public String[] getTypes() {
    return types;
  }
  public void setTypes(String[] types) {
    this.types = types;
  }
  public String getRentCostFrom() {
    return rentCostFrom;
  }
  public void setRentCostFrom(String rentCostFrom) {
    this.rentCostFrom = rentCostFrom;
  }
  public String getRentCostTo() {
    return rentCostTo;
  }
  public void setRentCostTo(String rentCostTo) {
    this.rentCostTo = rentCostTo;
  }
  public Long getStaffId() {
    return staffId;
  }
  public void setStaffId(Long staffId) {
    this.staffId = staffId;
  }
}
