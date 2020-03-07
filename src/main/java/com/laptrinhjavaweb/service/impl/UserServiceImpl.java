package com.laptrinhjavaweb.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.impl.UserRepositoryImpl;
import com.laptrinhjavaweb.service.UserService;

public class UserServiceImpl implements UserService{
  
  private UserRepository userRepository = new UserRepositoryImpl();
  private UserConverter userConverter = new UserConverter();
  
  @Override
  public List<UserDTO> findStaff(String roleCode) {
    List<UserEntity> entities = userRepository.findByRole(roleCode);
    List<UserDTO> result = entities.stream().map(item -> userConverter.convertEntityToDTO(item)).collect(Collectors.toList());
    return result;
  }

  @Override
  public List<UserDTO> findStaffManagerBuilding(String buildingId, String roleCode) {
    List<UserEntity> entities = userRepository.findByBuildingId(buildingId, roleCode);
//    List<UserEntity> users = userRepository.findByRole(roleCode);
    List<UserDTO> usersDto = this.findStaff(roleCode);
    List<UserDTO> result = entities.stream().map(item -> userConverter.convertEntityToDTO(item)).collect(Collectors.toList());
//    List<UserDTO> usersDto = users.stream().map(item -> userConverter.convertEntityToDTO(item)).collect(Collectors.toList());

    for (UserDTO userDTO : usersDto) {
      for (UserDTO userDTO2 : result) {
        if(userDTO.getUserName().equals(userDTO2.getUserName())) {
          userDTO.setChecked("checked");
        } else {
          userDTO.setChecked("");
        }
      } 
    }
    return usersDto;
  }
}
