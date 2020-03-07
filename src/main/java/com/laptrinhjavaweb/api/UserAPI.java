package com.laptrinhjavaweb.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.service.UserService;
import com.laptrinhjavaweb.service.impl.UserServiceImpl;

@WebServlet("/user")
public class UserAPI extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private UserService userService = new UserServiceImpl();
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    String action = request.getParameter("action");
    String roleCode = request.getParameter("roleCode");
    String buildingId = request.getParameter("buildingId");
    if(action != null && action.equals("LOAD_STAFF") && roleCode != null) {
      if(buildingId != null) {
        mapper.writeValue(response.getOutputStream(), userService.findStaffManagerBuilding(buildingId, roleCode));
      }
      mapper.writeValue(response.getOutputStream(), userService.findStaff(roleCode));
    }
  }
}
