package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
//    @Transactional
//    int deleteByIdAndUserId(Integer id, Integer user_id);

    @Query("SELECT m from Meal m LEFT JOIN FETCH m.user WHERE  m.id=:id")
    Meal getWithUser(@Param("id") Integer id);

    @Override
    Meal findOne(Integer id);

    @Override
    Meal save(Meal meal);

    @Transactional
    @Query("UPDATE Meal m SET m.calories=:calories, m.dateTime=:dateTime," +
            "m.description=:description, m.user=:user WHERE  m.id=:id")
    Integer update(@Param("id") int id, @Param("user") User user, @Param("description") String description,
                   @Param("dateTime") LocalDateTime localDateTime, @Param("calories") int calories);
//    @Modifying
//    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
//    List<Meal> findAll(@Param("userId") int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int user_id);

    //    @Modifying
//    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
//    List<Meal> findAllDateTime(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer user_id, LocalDateTime dateTime, LocalDateTime dateTime2);

}
