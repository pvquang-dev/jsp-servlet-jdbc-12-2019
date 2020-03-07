package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public class UserConverter {
  private ModelMapper mapper = new ModelMapper();

  public UserDTO convertEntityToDTO(UserEntity entity) {
    UserDTO userDTO = mapper.map(entity, UserDTO.class);
    return userDTO;
  }

  public UserEntity convertDTOToEntity(UserDTO dto) {
    UserEntity userEntity = mapper.map(dto, UserEntity.class);
    return userEntity;
  }
}
