package com.epam.izh.rd.online.entity;

import java.io.IOException;
import java.util.StringJoiner;

public class WithMatchesGame {

    private final int MAX_NUMBER_MATCHES_ON_TABLE;
    private final int MAX_NUMBER_MATCHES_PER_MOVE;
    private int currentNumberOfMatchesOnTable;
    private int currentNumberOfMatchesPerMove;
    private boolean computerMoves;

    public WithMatchesGame(int maxNumberMatchesOnTable, int maxNumberMatchesPerMove) {
        if (maxNumberMatchesOnTable <= 0 || maxNumberMatchesPerMove <= 0
                || maxNumberMatchesPerMove >= maxNumberMatchesOnTable) {
            try {
                throw new IOException("Ошибка при инициализации начальных параметров игры.");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        this.MAX_NUMBER_MATCHES_ON_TABLE = maxNumberMatchesOnTable;
        this.MAX_NUMBER_MATCHES_PER_MOVE = maxNumberMatchesPerMove;
        currentNumberOfMatchesOnTable = MAX_NUMBER_MATCHES_ON_TABLE;
        currentNumberOfMatchesPerMove = 0;
        computerMoves = true;
    }

    public int getMAX_NUMBER_MATCHES_ON_TABLE() {
        return MAX_NUMBER_MATCHES_ON_TABLE;
    }

    public int getMAX_NUMBER_MATCHES_PER_MOVE() {
        return MAX_NUMBER_MATCHES_PER_MOVE;
    }

    public int getCurrentNumberOfMatchesOnTable() {
        return currentNumberOfMatchesOnTable;
    }

    public void setCurrentNumberOfMatchesOnTable(int currentNumberOfMatchesOnTable) {
        this.currentNumberOfMatchesOnTable = currentNumberOfMatchesOnTable;
    }

    public int getCurrentNumberOfMatchesPerMove() {
        return currentNumberOfMatchesPerMove;
    }

    public void setCurrentNumberOfMatchesPerMove(int currentNumberOfMatchesPerMove) {
        this.currentNumberOfMatchesPerMove = currentNumberOfMatchesPerMove;
    }

    public boolean isComputerMoves() {
        return computerMoves;
    }

    public void setComputerMoves(boolean computerMoves) {
        this.computerMoves = computerMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithMatchesGame that = (WithMatchesGame) o;

        if (MAX_NUMBER_MATCHES_ON_TABLE != that.MAX_NUMBER_MATCHES_ON_TABLE) return false;
        if (MAX_NUMBER_MATCHES_PER_MOVE != that.MAX_NUMBER_MATCHES_PER_MOVE) return false;
        if (currentNumberOfMatchesOnTable != that.currentNumberOfMatchesOnTable) return false;
        if (currentNumberOfMatchesPerMove != that.currentNumberOfMatchesPerMove) return false;
        return computerMoves == that.computerMoves;
    }

    @Override
    public int hashCode() {
        int result = MAX_NUMBER_MATCHES_ON_TABLE;
        result = 31 * result + MAX_NUMBER_MATCHES_PER_MOVE;
        result = 31 * result + currentNumberOfMatchesOnTable;
        result = 31 * result + currentNumberOfMatchesPerMove;
        result = 31 * result + (computerMoves ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WithMatchesGame.class.getSimpleName() + "[", "]")
                .add("MAX_NUMBER_MATCHES_ON_TABLE=" + MAX_NUMBER_MATCHES_ON_TABLE)
                .add("MAX_NUMBER_MATCHES_PER_MOVE=" + MAX_NUMBER_MATCHES_PER_MOVE)
                .add("currentNumberOfMatchesOnTable=" + currentNumberOfMatchesOnTable)
                .add("currentNumberOfMatchesPerMove=" + currentNumberOfMatchesPerMove)
                .add("computerMoves=" + computerMoves)
                .toString();
    }
}
