package com.laptrinhjavaweb.service;

import java.util.List;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;

public interface AssignmentBuildingService {
  
  void delete(Long id);
  
  List<AssignmentBuildingDTO> update(AssignmentBuildingDTO dto);

}
