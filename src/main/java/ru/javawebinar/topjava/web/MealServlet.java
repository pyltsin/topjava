package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealRepositories;
import ru.javawebinar.topjava.service.Service;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pyltsin on 16.07.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static Service<Meal> service = new MealRepositories();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");
        List<Meal> list = service.getAll();
        List<MealWithExceed> listOut = MealsUtil.getFilteredWithExceededNotTime(list, 2000);
        request.setAttribute("meals", listOut);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
