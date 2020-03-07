package com.laptrinhjavaweb.dto;

public class BuildingDTO extends BaseDTO{
  private String name;
  private String ward;
  private String street;
  private String structure;
  private Integer numberOfBasement;
  private Integer floorArea;
  private String direction;
  private String rankz;
  private String rentAreaDescription;
  private String dictrict;
  private Integer rentCost;
  private String costDescription;
  private String serviceCost;
  private String carCost;
  private String motorCost;
  private String overtimeCost;
  private String type;
  private String electricBill;
  private String deposit;
  private String payment;
  private String timeRent;
  private String timeDecorator;
  private String managerName;
  private String managerPhone;
  private String[] types = new String[] {}; 

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWard() {
    return ward;
  }

  public void setWard(String ward) {
    this.ward = ward;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStructure() {
    return structure;
  }

  public void setStructure(String structure) {
    this.structure = structure;
  }

  public Integer getNumberOfBasement() {
    return numberOfBasement;
  }

  public void setNumberOfBasement(Integer numberOfBasement) {
    this.numberOfBasement = numberOfBasement;
  }


  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getRankz() {
    return rankz;
  }

  public void setRankz(String rankz) {
    this.rankz = rankz;
  }

  public String getDictrict() {
    return dictrict;
  }

  public void setDictrict(String dictrict) {
    this.dictrict = dictrict;
  }

  public Integer getRentCost() {
    return rentCost;
  }

  public void setRentCost(Integer rentCost) {
    this.rentCost = rentCost;
  }


  public String getCarCost() {
    return carCost;
  }

  public void setCarCost(String carCost) {
    this.carCost = carCost;
  }

  public String getMotorCost() {
    return motorCost;
  }

  public void setMotorCost(String motorCost) {
    this.motorCost = motorCost;
  }

  public String getOvertimeCost() {
    return overtimeCost;
  }

  public void setOvertimeCost(String overtimeCost) {
    this.overtimeCost = overtimeCost;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getElectricBill() {
    return electricBill;
  }

  public void setElectricBill(String electricBill) {
    this.electricBill = electricBill;
  }

  public String getDeposit() {
    return deposit;
  }

  public void setDeposit(String deposit) {
    this.deposit = deposit;
  }

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public String getTimeRent() {
    return timeRent;
  }

  public void setTimeRent(String timeRent) {
    this.timeRent = timeRent;
  }

  public String getTimeDecorator() {
    return timeDecorator;
  }

  public void setTimeDecorator(String timeDecorator) {
    this.timeDecorator = timeDecorator;
  }

  public String getManagerName() {
    return managerName;
  }

  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }

  public String getManagerPhone() {
    return managerPhone;
  }

  public void setManagerPhone(String managerPhone) {
    this.managerPhone = managerPhone;
  }

  public String[] getTypes() {
    return types;
  }

  public void setTypes(String[] types) {
    this.types = types;
  }

  public Integer getFloorArea() {
    return floorArea;
  }

  public void setFloorArea(Integer floorArea) {
    this.floorArea = floorArea;
  }

  public String getRentAreaDescription() {
    return rentAreaDescription;
  }

  public void setRentAreaDescription(String rentAreaDescription) {
    this.rentAreaDescription = rentAreaDescription;
  }

  public String getCostDescription() {
    return costDescription;
  }

  public void setCostDescription(String costDescription) {
    this.costDescription = costDescription;
  }

  public String getServiceCost() {
    return serviceCost;
  }

  public void setServiceCost(String serviceCost) {
    this.serviceCost = serviceCost;
  }
}
