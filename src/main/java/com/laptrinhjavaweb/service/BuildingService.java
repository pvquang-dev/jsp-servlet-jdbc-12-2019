package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;
import com.laptrinhjavaweb.api.output.BuildingTypeOutput;
import com.laptrinhjavaweb.buider.BuildingSearch;
import com.laptrinhjavaweb.dto.BuildingDTO;

public interface BuildingService {
  List<BuildingDTO> findAll(BuildingSearch buildingSearch);
  BuildingDTO save(BuildingDTO dto);
  List<BuildingTypeOutput> getBuldingType();
  Map<String, String> getMapBuldingType();
  BuildingDTO update(long id, BuildingDTO dto);
  void delete(Long[] ids);
}
