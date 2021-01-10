package com.epam.izh.rd.online.entity;

import java.util.StringJoiner;

public class GameProperties {
    private final int START_MATCHES_ON_TABLE;
    private final int MAX_MATCHES_PER_MOVE;

    public GameProperties(int START_MATCHES_ON_TABLE, int MAX_MATCHES_PER_MOVE) {
        this.START_MATCHES_ON_TABLE = START_MATCHES_ON_TABLE;
        this.MAX_MATCHES_PER_MOVE = MAX_MATCHES_PER_MOVE;
    }

    public int getSTART_MATCHES_ON_TABLE() {
        return START_MATCHES_ON_TABLE;
    }

    public int getMAX_MATCHES_PER_MOVE() {
        return MAX_MATCHES_PER_MOVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameProperties that = (GameProperties) o;

        if (START_MATCHES_ON_TABLE != that.START_MATCHES_ON_TABLE) return false;
        return MAX_MATCHES_PER_MOVE == that.MAX_MATCHES_PER_MOVE;
    }

    @Override
    public int hashCode() {
        int result = START_MATCHES_ON_TABLE;
        result = 31 * result + MAX_MATCHES_PER_MOVE;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GameProperties.class.getSimpleName() + "[", "]")
                .add("START_MATCHES_ON_TABLE=" + START_MATCHES_ON_TABLE)
                .add("MAX_MATCHES_PER_MOVE=" + MAX_MATCHES_PER_MOVE)
                .toString();
    }
}