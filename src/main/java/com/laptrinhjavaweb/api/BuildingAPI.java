package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.api.input.BuildingInput;
import com.laptrinhjavaweb.buider.BuildingSearch;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.service.impl.BuildingServiceImpl;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet("/building")
public class BuildingAPI extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  BuildingService service = new BuildingServiceImpl();

  public BuildingAPI() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    String action = request.getParameter("action");
    if (action != null && action.equals("SEARCH_BUILDING")) {
      BuildingInput buildingInput = FormUtil.toModel(BuildingInput.class, request);
      BuildingSearch buildingSearch = new BuildingSearch.Buider()
          .setName(buildingInput.getName())
          .setDictrict(buildingInput.getDictrict())
          .build();
      List<BuildingDTO> result = service.findAll(buildingSearch);
      mapper.writeValue(response.getOutputStream(), result);
    } else if(action != null && action.equals("GET_BUILDING_TYPE")) {
      mapper.writeValue(response.getOutputStream(), service.getMapBuldingType());
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    BuildingDTO dto = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
    dto = service.save(dto);
    mapper.writeValue(response.getOutputStream(), dto);
  }
  
  protected void doPut(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    String strId = request.getParameter("id");
    long id = Long.parseLong(strId);
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    BuildingDTO dto = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
    dto.setId(id);
    dto = service.update(id, dto);
    mapper.writeValue(response.getOutputStream(), dto);
  }
  
  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    BuildingDTO dto = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
    service.delete(dto.getIds());
    mapper.writeValue(response.getOutputStream(), "deleted");
  }
}
