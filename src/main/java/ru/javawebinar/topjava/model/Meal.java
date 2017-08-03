package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "meals")
@NamedQueries({
        @NamedQuery(name = Meal.UPDATE, query = "" +
                "update Meal m set m.calories=:calories," +
                "m.dateTime=:dateTime," +
                "m.description=:description where m.id=:id " +
                "and m.user.id=:userId"),
        @NamedQuery(name = Meal.DELETE, query = "" +
                "delete from Meal m  where m.id=:id and " +
                "m.user.id=:userId"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "" +
                "select m from Meal m where m.user.id=:userId order by m.dateTime desc "),
        @NamedQuery(name = Meal.BETWEEN, query = "" +
                "select m from Meal m where m.user.id=:userId and  m.dateTime between " +
                ":startDate and :endDate order by m.dateTime desc"),
})
public class Meal extends BaseEntity {

    public static final String UPDATE = "Meal.upate";
    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED = "Meal.allSorted";
    public static final String BETWEEN = "Meal.between";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "calories", nullable = false)
    @Range(min = 0, max = 10000)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
