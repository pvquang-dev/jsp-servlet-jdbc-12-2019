package com.laptrinhjavaweb.repository.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import com.laptrinhjavaweb.buider.BuildingSearch;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;

public class BuildingRepositoryImpl extends SimpleJPARepository<BuildingEntity>
    implements BuildingRepository {

  @Override
  public List<BuildingEntity> findAll(Map<String, Object> params, BuildingSearch buildingSearch) {
    StringBuilder sql = new StringBuilder("select * from building b ");
    if(buildingSearch.getStaffId() != null && buildingSearch.getStaffId() != -1)  {
      sql.append(" inner join assignmentbuilding ab on b.id = ab.buildingid");
    }
    sql.append("where 1=1 ");
    sql = this.createSQLfindAllCommon(params, sql);
    sql = createSQLspecial(sql, buildingSearch);
    return this.findAll(sql.toString());
  }

  private StringBuilder createSQLspecial(StringBuilder sql, BuildingSearch buildingSearch) {
    if (StringUtils.isNotBlank(buildingSearch.getRentAreaFrom())
        || StringUtils.isNotBlank(buildingSearch.getRentAreaTo())) {
      sql.append(" and exists (select * from rentarea ra where (b.id = ra.buildingid");
      if (StringUtils.isNotBlank(buildingSearch.getRentAreaFrom())) {
        sql.append(" and ra.value >= " + buildingSearch.getRentAreaFrom() + " ");
      }
      if (StringUtils.isNotBlank(buildingSearch.getRentAreaTo())) {
        sql.append(" and ra.value <= " + buildingSearch.getRentAreaTo() + " ");
      }
      sql.append("))");
    }
    //java7
//    if(buildingSearch.getTypes().length > 0) {
//      sql.append(" and (");
//      for(String type : buildingSearch.getTypes()) {
//        if(type.equals(buildingSearch.getTypes()[0])) {
//          sql.append(" tableName.type like '%"+type+"%'");
//        } else {
//          sql.append(" or tableName.type like '%"+type+"%'");
//        }
//      }
//      sql.append(")");
//    }
    // java 8
    if(buildingSearch.getTypes().length > 0) {
      sql.append(" and (");
      String sqlType = Arrays.stream(buildingSearch.getTypes())
          .map(item -> "(b.type like '%"+item+"%')")
          .collect(Collectors.joining(" or "));
      sql.append(sqlType);
      sql.append(")");
    }
    if(StringUtils.isNotBlank(buildingSearch.getRentCostFrom())) {
      sql.append(" and b.rentcost >= "+buildingSearch.getRentCostFrom()+"");
    }
    if(StringUtils.isNotBlank(buildingSearch.getRentCostTo())) {
      sql.append(" and b.rentcost <= "+buildingSearch.getRentCostTo()+"");
    }
    if(buildingSearch.getStaffId() != null && buildingSearch.getStaffId() != -1)  {
      sql.append(" and ab.staffId = "+buildingSearch.getStaffId()+"");
    }
    return sql;
  }

  @Override
  public BuildingEntity findById(Long buildingId) {
    StringBuilder sql = new StringBuilder("select * from building b");
    if(buildingId != null)  {
      sql.append(" where b.id =  "+buildingId+"");
    }
    List<BuildingEntity> lst = this.findAll(sql.toString());
    return lst.get(0);
  }

  @Override
  public void delete(Long id) {
    String sql = "DELETE FROM building WHERE id = ?";
    this.update(sql, id);
  }
}
