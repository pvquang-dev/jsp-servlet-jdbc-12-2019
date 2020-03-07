package com.laptrinhjavaweb.converter;

import org.modelmapper.*;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class BuildingConverter {

  private ModelMapper mapper = new ModelMapper();

  public BuildingDTO convertEntityToDTO(BuildingEntity entity) {
    BuildingDTO buildingdto = mapper.map(entity, BuildingDTO.class);
    return buildingdto;
  }

  public BuildingEntity convertDTOToEntity(BuildingDTO dto) {
    BuildingEntity buildingEntity = mapper.map(dto, BuildingEntity.class);
    return buildingEntity;
  }
}
