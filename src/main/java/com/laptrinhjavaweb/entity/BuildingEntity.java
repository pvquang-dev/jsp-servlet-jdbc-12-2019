package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "ward")
  private String ward;

  @Column(name = "street")
  private String street;

  @Column(name = "structure")
  private String structure;

  @Column(name = "numberofbasement")
  private Integer numberOfBasement;

  @Column(name = "floorarea")
  private Integer floorArea;

  @Column(name = "direction")
  private String direction;

  @Column(name = "rankz")
  private String rankz;

  @Column(name = "rentareadescription")
  private String rentareaDescription;

  @Column(name = "dictrict")
  private String dictrict;

  @Column(name = "rentcost")
  private Integer rentCost;

  @Column(name = "costdescription")
  private String costDescription;

  @Column(name = "servicecost")
  private String serviceCost;

  @Column(name = "carcost")
  private String carCost;

  @Column(name = "motocost")
  private String motorCost;

  @Column(name = "overtimecost")
  private String overtimeCost;

  @Column(name = "type")
  private String type;

  @Column(name = "electricbuill")
  private String electricBill;

  @Column(name = "deposit")
  private String deposit;

  @Column(name = "payment")
  private String payment;

  @Column(name = "timerent")
  private String timeRent;

  @Column(name = "timedecorator")
  private String timeDecorator;

  @Column(name = "managername")
  private String managerName;

  @Column(name = "managerphone")
  private String managerPhone;

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

  public Integer getFloorArea() {
    return floorArea;
  }

  public void setFloorArea(Integer floorArea) {
    this.floorArea = floorArea;
  }

  public String getRentareaDescription() {
    return rentareaDescription;
  }

  public void setRentareaDescription(String rentareaDescription) {
    this.rentareaDescription = rentareaDescription;
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
