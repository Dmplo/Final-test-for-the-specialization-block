package controllers;

import Infrastructure.validarors.StringValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Animal;
import models.Pagination;
import services.AllAnimalsService;
import services.ValidationService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "allAnimalsController", urlPatterns = "/animals")

public class AllAnimalsController extends HttpServlet {

    int pageSize = 2;
    AllAnimalsService allAnimalsService = new AllAnimalsService(this.pageSize);
    ValidationService validationService = new ValidationService(new StringValidator());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int currentPageNumber = request.getParameterMap().containsKey("page") ? Integer.parseInt(request.getParameter("page")) : 1;
        List<Animal> animals = null;
        try {
            if (request.getParameterMap().containsKey("filter_by")) {

                String animalName = request.getParameter("filter_by");
                List<String> violations = this.validationService.validateStr(animalName);
                if (!violations.isEmpty()) {
                    request.setAttribute("violations", violations);
                } else {
                    request.setAttribute("animalName", animalName);
                    animals = this.allAnimalsService.getAnimalsByParams(currentPageNumber, animalName);
                }
            } else {
                animals = this.allAnimalsService.getAnimalsByPageNumber(currentPageNumber);
            }
            Pagination<Animal> pagination = this.allAnimalsService.getPaginateAnimals();
            request.setAttribute("animals", animals);
            request.setAttribute("pagination", pagination);
        } catch (Exception e) {
            request.setAttribute("exception", "Sorry, no data available.");
        }
        request.getRequestDispatcher("/views/all-animals.jsp").forward(request, response);
    }
}
