package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.User;
import com.epam.izh.rd.online.exceptions.NotFoundException;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;

public class RamUserRepository {
    private User[] gameUsers;

    public RamUserRepository() {
        gameUsers = new User[0];
    }

    public int count() {
        return gameUsers.length;
    }

    public int save(@NotNull User user) {
        int countUsers = count();
        gameUsers = Arrays.copyOf(gameUsers, countUsers + 1);
        gameUsers[countUsers] = new User(user);
        return ++countUsers;
    }

    public User read(int id) {
        User user;
        try {
            user = new User(gameUsers[id]);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException("Игрок с идентификатором id = " + id + " не существует.");
        }
        return user;
    }

    public String findNameById(int id) {
        return gameUsers[id].getName();
    }

    public int nextId(int id) {
        return (id + 1) % count();
    }
}