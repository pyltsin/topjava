package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;
import ru.javawebinar.topjava.util.DbPopulator;

import static org.testng.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
    }

    @Test
    public void testGetAll() throws Exception {
    }

    @Test
    public void testUpdate() throws Exception {
    }

    @Test
    public void testSave() throws Exception {
    }

    @Test
    public void testFailedUpdate() throws Exception {
    }


    @Test
    public void testFailedGet() throws Exception {
    }

    @Test
    public void testFailedDelete() throws Exception {
    }

}