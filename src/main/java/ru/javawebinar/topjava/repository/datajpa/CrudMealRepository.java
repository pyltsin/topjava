package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    //    @Transactional
//    @Modifying
//    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
//    int delete(@Param("id") int id, @Param("userId") int userId);
    @Transactional
    int deleteByIdAndUserId(Integer id, Integer user_id);

    @Override
    Meal findOne(Integer id);

    @Override
    @Transactional
    Meal save(Meal meal);

//    @Modifying
//    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
//    List<Meal> findAll(@Param("userId") int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int user_id);

    //    @Modifying
//    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
//    List<Meal> findAllDateTime(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer user_id, LocalDateTime dateTime, LocalDateTime dateTime2);

}
