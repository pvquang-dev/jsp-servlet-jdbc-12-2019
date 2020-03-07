package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;

public class AssignmentBuildingConverter {
  private ModelMapper mapper = new ModelMapper();

  public AssignmentBuildingDTO convertEntityToDTO(AssignmentBuildingEntity entity) {
    AssignmentBuildingDTO assignmentBuildingDTO = mapper.map(entity, AssignmentBuildingDTO.class);
    return assignmentBuildingDTO;
  }

  public AssignmentBuildingEntity convertDTOToEntity(AssignmentBuildingDTO dto) {
    AssignmentBuildingEntity assignmentBuildingEntity = mapper.map(dto, AssignmentBuildingEntity.class);
    return assignmentBuildingEntity;
  }
}
