package controllers;


import Infrastructure.entityService.ServiceFactory;
import Infrastructure.entityService.iAnimalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Animal;

import java.io.IOException;

@WebServlet(name = "indexController", value = "")

public class IndexController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            iAnimalService animalService = ServiceFactory.getService(iAnimalService.class);
            assert animalService != null;
            Animal animal = animalService.getLastAnimal();

            if (animal != null) {
                request.setAttribute("animal", animal);
            } else {
                throw new Exception("Animal is not found");
            }
        } catch (Exception e) {
            request.setAttribute("exception", "Sorry, no data available. " + e.getMessage());
        }
        request.getRequestDispatcher("/views/farm.jsp").forward(request, response);
    }
}
