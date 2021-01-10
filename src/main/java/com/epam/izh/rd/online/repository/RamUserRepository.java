package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.User;
import com.epam.izh.rd.online.exceptions.NotFoundException;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;

public class RamUserRepository implements UserRepository {
    private User[] gameUsers;

    public RamUserRepository() {
        gameUsers = new User[0];
    }

    @Override
    public int count() {
        return gameUsers.length;
    }

    @Override
    public int save(@NotNull User user) {
        int countUsers = count();
        gameUsers = Arrays.copyOf(gameUsers, countUsers + 1);
        gameUsers[countUsers] = new User(user);
        return ++countUsers;
    }

    @Override
    public User read(int id) {
        User user;
        try {
            user = new User(gameUsers[id]);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Игрок с идентификатором id = " + id + " не существует.");
        }
        return user;
    }

    @Override
    public String findNameById(int id) {
        return gameUsers[id].getName();
    }

    @Override
    public int nextId(int id) {
        return (id + 1) % count();
    }
}