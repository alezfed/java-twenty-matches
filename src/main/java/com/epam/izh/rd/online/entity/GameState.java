package com.epam.izh.rd.online.entity;

import java.util.StringJoiner;

public class GameState {
    private PossibleGameStates id;
    private int matchesPerMove;
    private int matchesOnTable;
    private int userID;

    public GameState(PossibleGameStates id, int matchesOnTable, int matchesPerMove, int userID) {
        this.id = id;
        this.matchesPerMove = matchesPerMove;
        this.matchesOnTable = matchesOnTable;
        this.userID = userID;
    }

    public GameState(GameState state) {
        this.id = state.id;
        this.matchesPerMove = state.matchesPerMove;
        this.matchesOnTable = state.matchesOnTable;
        this.userID = state.userID;
    }

    public PossibleGameStates getId() {
        return id;
    }

    public void setId(PossibleGameStates id) {
        this.id = id;
    }

    public int getMatchesPerMove() {
        return matchesPerMove;
    }

    public void setMatchesPerMove(int matchesPerMove) {
        this.matchesPerMove = matchesPerMove;
    }

    public int getMatchesOnTable() {
        return matchesOnTable;
    }

    public void setMatchesOnTable(int matchesOnTable) {
        this.matchesOnTable = matchesOnTable;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameState gameState = (GameState) o;

        if (matchesPerMove != gameState.matchesPerMove) return false;
        if (matchesOnTable != gameState.matchesOnTable) return false;
        if (userID != gameState.userID) return false;
        return id == gameState.id;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + matchesPerMove;
        result = 31 * result + matchesOnTable;
        result = 31 * result + userID;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GameState.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("matchesPerMove=" + matchesPerMove)
                .add("matchesOnTable=" + matchesOnTable)
                .add("userID=" + userID)
                .toString();
    }
}