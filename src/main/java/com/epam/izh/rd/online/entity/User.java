package com.epam.izh.rd.online.entity;

import java.util.StringJoiner;

public class User {
    private int id;
    private String name;
    private boolean iAmComputer;

    public User(int id, String name, boolean IAmComputer) {
        this.id = id;
        this.name = name;
        this.iAmComputer = IAmComputer;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.iAmComputer = name.equals("Компьютер");
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.iAmComputer = user.iAmComputer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIAmComputer() {
        return iAmComputer;
    }

    public void setIAmComputer(boolean IAmComputer) {
        this.iAmComputer = IAmComputer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (iAmComputer != user.iAmComputer) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (iAmComputer ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("IAmComputer=" + iAmComputer)
                .toString();
    }
}