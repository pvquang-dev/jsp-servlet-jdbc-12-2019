package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;
import com.laptrinhjavaweb.buider.BuildingSearch;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepository extends JPARepository<BuildingEntity> {

  List<BuildingEntity> findAll(Map<String, Object> params, BuildingSearch buildingSearch);

  BuildingEntity findById(Long buildingId);

  void delete(Long id);
 
}
