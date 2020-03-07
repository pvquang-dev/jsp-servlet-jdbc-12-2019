package com.laptrinhjavaweb.api;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.AssignmentBuildingDTO;
import com.laptrinhjavaweb.service.AssignmentBuildingService;
import com.laptrinhjavaweb.service.impl.AssignmentBuildingServiceImpl;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet("/assignmentbuilding")
public class AssignmentBuildingAPI extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private AssignmentBuildingService assignmentBuildingService = new AssignmentBuildingServiceImpl();
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    AssignmentBuildingDTO dto = HttpUtil.of(request.getReader()).toModel(AssignmentBuildingDTO.class);
    List<AssignmentBuildingDTO> result = new ArrayList<>();
    result = assignmentBuildingService.update(dto);
    mapper.writeValue(response.getOutputStream(), result);
  }
}
