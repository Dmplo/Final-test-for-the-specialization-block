package controllers;


import Infrastructure.validarors.StringValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Animal;
import models.Command;
import services.ProfileService;
import services.ValidationService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "profileController", value = "/profile")
@MultipartConfig

public class ProfileController extends HttpServlet {

    ValidationService validationService = new ValidationService(new StringValidator());
    ProfileService profileService = new ProfileService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("animalId"));
            Animal animal = this.profileService.getById(id);
            List<Command> commands = this.profileService.getCommands(animal.getAnimalsCommands());
            request.setAttribute("animal", animal);
            request.setAttribute("commands", commands);
        } catch (Exception e) {
            request.setAttribute("exception", "Sorry, no data available.");
        }
        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String json;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            String name = request.getParameter("name");
            String text = request.getParameter("text");
            long id = Long.parseLong(request.getParameter("animalId"));
            List<String> violations = this.validationService.validateUserData(name, text, id);
            if (!violations.isEmpty()) {
                json = mapper.writeValueAsString(violations);
            } else {
                Animal animal = this.profileService.getById(id);
              if (!this.validationService.validateCommand(animal.getAnimalsCommands(), name)) {
                  this.profileService.addCommand(animal, name, text);
                  json = mapper.writeValueAsString(animal);
              } else {
                  json = String.format("{\"error\": \"Animal already knows this command - %s!\"}", name.toUpperCase());
              }
            }
        } catch (Exception e) {
            json = "{\"exception\": \"Input data is not correct!\"}";
        }
        assert json != null;
        response.getWriter().write(json);
    }
}
