package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), mealService.getAll(USER_ID));
    }

    @Test
    public void testGetForUpdate() throws Exception {
        mockMvc.perform(get(REST_URL + "update?id=" + MEAL1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testGetBetween() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(REST_URL + "by?startDate=2015-05-30&endDate=2015-05-30"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        List<MealWithExceed> result = JsonUtil.readValues(TestUtil.getContent(resultActions), MealWithExceed.class);
        List<MealWithExceed> expected = Arrays.asList(new MealWithExceed( MEAL3, false),
                new MealWithExceed( MEAL2, false), new MealWithExceed( MEAL1, false));
        MATCHEREXCEED.assertListEquals(result, expected);
    }

    @Test
    public void testGetForCreate() throws Exception {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000);

        mockMvc.perform(get(REST_URL + "create"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(meal));
    }


    @Test
    public void testUpdate() throws Exception {
        Meal updated = MEAL1;
        updated.setCalories(1234);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(updated));

        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, USER_ID));

        Meal created = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "паривет", 1000);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Meal returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());
        MATCHER.assertEquals(created, returned);

        MATCHER.assertEquals(created, mealService.get(created.getId(), USER_ID));
    }

}