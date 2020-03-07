package com.laptrinhjavaweb.repository;

import java.util.List;
import com.laptrinhjavaweb.entity.UserEntity;

public interface UserRepository extends JPARepository<UserEntity>{
  List<UserEntity> findByRole(String role);

  List<UserEntity> findByBuildingId(String buildingId, String roleCode);
}
