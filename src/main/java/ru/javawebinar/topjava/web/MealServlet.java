package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (controller == null) {
            try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
                controller = appCtx.getBean(MealRestController.class);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("idUser") != null) {
            AuthorizedUser.setId(Integer.valueOf(request.getParameter("idUser")));
            response.sendRedirect("meals");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");


        int userId = AuthorizedUser.id();

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                userId, LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        if (meal.isNew()) {
            controller.create(meal);
        } else {
            try {
                controller.update(meal, Integer.valueOf(id));
            } catch (NotFoundException e) {
                sendErrorPage(response);
                return;
            }
        }
        response.sendRedirect("meals");
    }

    private void sendErrorPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = AuthorizedUser.id();

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                try {
                    controller.delete(id);
                } catch (NotFoundException e) {
                    sendErrorPage(response);
                    return;
                }
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal;
                try {
                    meal = "create".equals(action) ?
                            new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, userId) :
                            controller.get(getId(request));
                    request.setAttribute("meal", meal);

                } catch (NotFoundException e) {
                    sendErrorPage(response);
                    return;
                }
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "filterDate":

                break;
            case "filterTime":

                break;

            case "filterDateTime":

                break;

            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        controller.getAll(2000));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
