package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    public static final int CALORIES = 1000;

    private MealRestController controller;
    private ConfigurableApplicationContext appCtx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (controller == null || appCtx == null) {
             appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
            controller = appCtx.getBean(MealRestController.class);
        }
    }


    @Override
    public void destroy() {
        appCtx.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                            new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", CALORIES, userId) :
                            controller.get(getId(request));
                    request.setAttribute("meal", meal);

                } catch (NotFoundException e) {
                    sendErrorPage(response);
                    return;
                }
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "filterDate":
                try {
                    String startDateString = request.getParameter("startDate");
                    String endDateString = request.getParameter("endDate");

                    if (startDateString == null ||startDateString.equals("")
                            || endDateString == null || endDateString.equals("")) {
                        sendErrorPage(response);
                        return;
                    }
                    LocalDate start = LocalDate.parse(startDateString);
                    LocalDate end = LocalDate.parse(endDateString);
                    log.info("getFilterDate {} - {}", start, end);


                    List<MealWithExceed> list = controller.getAllBetweenDate(CALORIES, start, end);
                    request.setAttribute("meals", list);
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);

                } catch (Exception e) {
                    sendErrorPage(response);
                    return;
                }
                break;
            case "filterTime":

                try {
                    String startTimeString = request.getParameter("startTime");
                    String endTimeString = request.getParameter("endTime");

                    if (startTimeString == null ||startTimeString.equals("")
                            || endTimeString == null || endTimeString.equals("")) {
                        sendErrorPage(response);
                        return;
                    }
                    LocalTime start = LocalTime.parse(startTimeString);
                    LocalTime end = LocalTime.parse(endTimeString);
                    log.info("getFilterDate {} - {}", start, end);


                    List<MealWithExceed> list = controller.getAllBetweenTime(CALORIES, start, end);
                    request.setAttribute("meals", list);
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);

                } catch (Exception e) {
                    sendErrorPage(response);
                    return;
                }
                break;

            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        controller.getAll(CALORIES));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
