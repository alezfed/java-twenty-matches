package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.User;

public interface UserRepository {

    int count();

    int save(User user);

    User read(int id);

    String findNameById(int id);

    int nextId(int id);
}