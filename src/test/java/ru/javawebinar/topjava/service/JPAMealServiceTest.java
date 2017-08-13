package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Pyltsin on 10.08.2017.
 */
@ActiveProfiles(Profiles.JPA)
public class JPAMealServiceTest extends MealServiceTConcrete {
}
