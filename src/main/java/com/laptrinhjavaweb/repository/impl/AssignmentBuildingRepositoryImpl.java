package com.laptrinhjavaweb.repository.impl;

import java.util.List;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;

public class AssignmentBuildingRepositoryImpl extends SimpleJPARepository<AssignmentBuildingEntity> implements AssignmentBuildingRepository{
  @Override
  public AssignmentBuildingEntity findById(Long buildingId) {
    StringBuilder sql = new StringBuilder("select * from assignmentbuilding asb");
    if(buildingId != null)  {
      sql.append(" where asb.buildingid =  "+buildingId+"");
    }
    List<AssignmentBuildingEntity> lst = this.findAll(sql.toString());
    return lst.get(0);
  }

  @Override
  public void delete(Long id) {
    String sql = "DELETE FROM assignmentbuilding WHERE buildingid = ?";
    this.update(sql, id);
  }
}
