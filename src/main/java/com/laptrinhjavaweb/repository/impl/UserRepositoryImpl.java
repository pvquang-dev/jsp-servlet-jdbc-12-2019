package com.laptrinhjavaweb.repository.impl;

import java.util.List;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;

public class UserRepositoryImpl extends SimpleJPARepository<UserEntity> implements UserRepository {

  @Override
  public List<UserEntity> findByRole(String role) {
    StringBuilder sql = new StringBuilder("select * from user u");
    sql.append(" inner join user_role ur on u.id = ur.userid");
    sql.append(" inner join role r on r.id = ur.roleid");
    sql.append(" where r.code = '"+role+"'");
    return this.findAll(sql.toString());
  }

  @Override
  public List<UserEntity> findByBuildingId(String buildingId, String roleCode) {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT * FROM user");
    sql.append("    inner join user_role on user.id = user_role.userid");
    sql.append("    inner join role on role.id = user_role.roleid");
    sql.append("    inner join assignmentbuilding");
    sql.append("    on user.id = assignmentbuilding.staffid");
    sql.append("    where role.code = '"+roleCode+"' and assignmentbuilding.buildingid = '"+buildingId+"'");
    return this.findAll(sql.toString());
  }
}

