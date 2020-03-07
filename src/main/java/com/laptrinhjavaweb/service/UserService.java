package com.laptrinhjavaweb.service;

import java.util.List;
import com.laptrinhjavaweb.dto.UserDTO;

public interface UserService {
  List<UserDTO> findStaff(String roleCode);

  List<UserDTO> findStaffManagerBuilding(String buildingId, String roleCode);

}
