package com.laptrinhjavaweb.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.laptrinhjavaweb.converter.AssignmentBuildingConverter;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.impl.AssignmentBuildingRepositoryImpl;
import com.laptrinhjavaweb.service.AssignmentBuildingService;

public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

  private AssignmentBuildingRepository assignmentBuildingRepository = new AssignmentBuildingRepositoryImpl();
  private AssignmentBuildingConverter converter = new AssignmentBuildingConverter();

  @Override
  public List<AssignmentBuildingDTO> update(AssignmentBuildingDTO dto) {
    this.delete(dto.getBuildingId());
    AssignmentBuildingEntity entity = new AssignmentBuildingEntity();
    for (Long id : dto.getIds()) {
      entity.setBuildingId(dto.getBuildingId());
      entity.setStaffId(id);
      assignmentBuildingRepository.insert(entity); 
    }
    StringBuilder sql = new StringBuilder();
    sql.append("select * from assignmentbuilding ");
    sql.append("where buildingid = "+dto.getBuildingId()+"");
    List<AssignmentBuildingEntity> entities = assignmentBuildingRepository.findAll(sql.toString());
    List<AssignmentBuildingDTO> results = entities.stream().map(item -> converter.convertEntityToDTO(item))
        .collect(Collectors.toList());
    return results;
  }

  @Override
  public void delete(Long id) {
    assignmentBuildingRepository.delete(id);
  }
}
