package controllers;

import Infrastructure.validarors.StringValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Animal;
import Infrastructure.enums.Types;
import services.AnimalsCommandsService;
import services.ValidationService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "createAnimalsController", urlPatterns = "/new-animal")

public class CreateAnimalsController extends HttpServlet {

    private final ValidationService validationService = new ValidationService(new StringValidator());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String birth = request.getParameter("birth");
        List<String> violations = this.validationService.validateNewAnimal(name, type, birth);
        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
        } else {
            try {
                AnimalsCommandsService animalsCommandsService = new AnimalsCommandsService();
                Animal animal = animalsCommandsService.createOneAnimal(name, type, LocalDate.parse(birth));
                request.setAttribute("animal", animal);
                request.setAttribute("create", true);
                request.getRequestDispatcher("/views/farm.jsp").forward(request, response);
                return;
            } catch (Exception e) {
                request.setAttribute("violations", "Sorry, no data available. " + e.getMessage());
            }
        }
        request.setAttribute("name", name);
        request.setAttribute("type", type);
        request.setAttribute("birth", birth);
        request.setAttribute("types", Types.values());
        request.getRequestDispatcher("/views/create-animal.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("types", Types.values());
        request.getRequestDispatcher("/views/create-animal.jsp").forward(request, response);
    }
}