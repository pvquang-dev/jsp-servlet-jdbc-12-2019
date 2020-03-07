package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;

public interface AssignmentBuildingRepository extends JPARepository<AssignmentBuildingEntity> {

  AssignmentBuildingEntity findById(Long buildingId);

  void delete(Long id);
}
