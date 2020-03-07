package com.laptrinhjavaweb.buider;

public class BuildingSearch {

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

  public BuildingSearch(Buider builder) {
    this.name = builder.name;
    this.dictrict = builder.dictrict;
    this.numberofbasement = builder.numberofbasement;
    this.floorArea = builder.floorArea;
    this.rentAreaFrom = builder.rentAreaFrom;
    this.rentAreaTo = builder.rentAreaTo;
    this.types = builder.types;
    this.rentCostFrom = builder.rentCostFrom;
    this.rentCostTo = builder.rentCostTo;
    this.staffId = builder.staffId;
  }

  public String getName() {
    return name;
  }

  public String getDictrict() {
    return dictrict;
  }

  public String getNumberofbasement() {
    return numberofbasement;
  }

  public String getFloorArea() {
    return floorArea;
  }

  public String getRentAreaFrom() {
    return rentAreaFrom;
  }

  public String getRentAreaTo() {
    return rentAreaTo;
  }

  public String[] getTypes() {
    return types;
  }

  public String getRentCostFrom() {
    return rentCostFrom;
  }

  public String getRentCostTo() {
    return rentCostTo;
  }

  public Long getStaffId() {
    return staffId;
  }

  public static class Buider {
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

    public Buider setName(String name) {
      this.name = name;
      return this;
    }

    public Buider setDictrict(String dictrict) {
      this.dictrict = dictrict;
      return this;
    }

    public Buider setNumberofbasement(String numberofbasement) {
      this.numberofbasement = numberofbasement;
      return this;
    }

    public Buider setFloorArea(String floorArea) {
      this.floorArea = floorArea;
      return this;
    }

    public Buider setRentAreaFrom(String rentAreaFrom) {
      this.rentAreaFrom = rentAreaFrom;
      return this;
    }

    public Buider setRentAreaTo(String rentAreaTo) {
      this.rentAreaTo = rentAreaTo;
      return this;
    }

    public Buider setTypes(String[] types) {
      this.types = types;
      return this;
    }

    public Buider setRentCostFrom(String rentCostFrom) {
      this.rentCostFrom = rentCostFrom;
      return this;
    }

    public Buider setRentCostTo(String rentCostTo) {
      this.rentCostTo = rentCostTo;
      return this;
    }
    
    public Buider setStaffId(Long staffId) {
      this.staffId = staffId;
      return this;
    }
    
    public BuildingSearch build() {
      return new BuildingSearch(this);
    }
  }
}
