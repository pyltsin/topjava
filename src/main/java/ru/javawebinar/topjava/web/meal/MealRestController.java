package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.formatter.MyDateTime;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal update(@RequestParam("id") int id) {
        return super.get(id);
    }

    @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal create() {
        return new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> updateOrCreate(@RequestBody Meal meal) {

        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, meal.getId());
        }
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(meal.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(meal);
    }

    @PostMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(@RequestParam(value = "startDate", required = false)
                                           @MyDateTime(iso = MyDateTime.ISO.DATE) LocalDate startLocalDate,
                                           @RequestParam(value = "startTime", required = false)
                                           @MyDateTime(iso = MyDateTime.ISO.TIME) LocalTime startLocalTime,
                                           @RequestParam(value = "endDate", required = false)
                                           @MyDateTime(iso = MyDateTime.ISO.DATE) LocalDate endLocalDate,
                                           @RequestParam(value = "endTime", required = false)
                                           @MyDateTime(iso = MyDateTime.ISO.TIME) LocalTime endLocalTime) {
        return super.getBetween(startLocalDate, startLocalTime,
                endLocalDate, startLocalTime);
    }
}