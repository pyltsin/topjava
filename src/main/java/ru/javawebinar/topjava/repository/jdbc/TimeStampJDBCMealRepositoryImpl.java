package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Pyltsin on 10.08.2017.
 */
@Repository
@Profile("hsqldb")
public class TimeStampJDBCMealRepositoryImpl extends AbstractJdbcMealRepositoryImpl<Timestamp> {
    public TimeStampJDBCMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    Timestamp getDate(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }
}
