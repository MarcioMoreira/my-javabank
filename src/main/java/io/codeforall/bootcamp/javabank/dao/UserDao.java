package io.codeforall.bootcamp.javabank.dao;

import java.util.List;

public interface UserDao<User> {

    // basic crud methods
    List<User> findAll();
    User findById(Integer id);
    User saveOrUpdate(User user);
    void delete(Integer id);

    // additional methods
//    User findByUsername(String username);
//    User findByEmail(String email);

}