package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import com.laptrinhjavaweb.api.output.BuildingTypeOutput;
import com.laptrinhjavaweb.buider.BuildingSearch;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavaweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {

  private BuildingRepository buildingRepository = new BuildingRepositoryImpl();
  private BuildingConverter converter = new BuildingConverter();

  @Override
  public List<BuildingDTO> findAll(BuildingSearch builder) {
    Map<String, Object> params = convertToMapProperties(builder);
//    Map<String, Object> params = new HashMap<>();
//    params.put("name", builder.getName());
//    params.put("dictrict", builder.getDictrict());
//    params.put("floorarea",
//        (StringUtils.isNotBlank(builder.getFloorArea())) ? Integer.parseInt(builder.getFloorArea())
//            : null);
//    params.put("numberofbasement",
//        (StringUtils.isNotBlank(builder.getNumberofbasement()))
//            ? Integer.parseInt(builder.getNumberofbasement())
//            : null);
    List<BuildingEntity> entities = buildingRepository.findAll(params, builder);
    List<BuildingDTO> results = entities.stream().map(item -> converter.convertEntityToDTO(item))
        .collect(Collectors.toList());
//    List<BuildingDTO> results = new ArrayList<>();
//    for (BuildingEntity item : entities) {
//      BuildingDTO dto = converter.convertEntityToDTO(item);
//      results.add(dto);
//    }
    return results;
  }

  private Map<String, Object> convertToMapProperties(BuildingSearch buildingSearch) {
    Map<String, Object> properties = new HashMap<>();
    try {
      Field[] fields = BuildingSearch.class.getDeclaredFields();
      for (Field field : fields) {
        if (!field.getName().startsWith("rentArea") 
            && !field.getName().equals("types")
            && !field.getName().equals("staffId")
            && !field.getName().startsWith("rentCost")) {
          field.setAccessible(true);
          if (field.get(buildingSearch) instanceof String) {
            properties.put(field.getName().toLowerCase(), field.get(buildingSearch));
          } else {
            if (field.get(buildingSearch) != null
                && StringUtils.isEmpty((String) field.get(buildingSearch))) {
              properties.put(field.getName().toLowerCase(),
                  Integer.parseInt((String) field.get(buildingSearch)));
            }
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return properties;
  }

  @Override
  public BuildingDTO save(BuildingDTO dto) {
    BuildingEntity entity =  converter.convertDTOToEntity(dto);
    Long buildingId = buildingRepository.insert(entity);
    return converter.convertEntityToDTO(buildingRepository.findById(buildingId));
  }
  
  @Override
  public BuildingDTO update(long id, BuildingDTO dto) {
    BuildingEntity entity =  converter.convertDTOToEntity(dto);
    buildingRepository.update(id, entity);
    return converter.convertEntityToDTO(buildingRepository.findById(id));
  }
  
  @Override
  public List<BuildingTypeOutput> getBuldingType() {
    List<BuildingTypeOutput> result = new ArrayList<>();
    for(BuildingTypeEnum item  : BuildingTypeEnum.values()) {
      BuildingTypeOutput output = new BuildingTypeOutput();
      output.setCode(item.toString());
      output.setName(item.getValue());
      result.add(output);
    }
    return result;
  }

  @Override
  public Map<String, String> getMapBuldingType() {
    Map<String, String> result = new HashMap<>();
    for(BuildingTypeEnum item  : BuildingTypeEnum.values()) {
      result.put(item.toString(), item.getValue());
    }
    return null;
  }

  @Override
  public void delete(Long[] ids) {
    for (Long id : ids) {
      buildingRepository.delete(id);
    }
  }
}
