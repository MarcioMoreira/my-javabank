package io.codeforall.bootcamp.javabank.dao;

import java.util.List;

public class AccountDao implements UserDao{
    @Override
    public List findAll() {
        List<Object> objects = List.of();
        return objects;
    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public Object saveOrUpdate(Object o) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
