package ru.javawebinar.topjava.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        try {
            return service.get(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        try {
            service.delete(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        checkIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        try {
            return service.getByEmail(email);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}