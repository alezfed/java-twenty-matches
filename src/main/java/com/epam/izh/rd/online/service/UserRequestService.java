package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.GameProperties;
import com.epam.izh.rd.online.entity.GameState;
import com.epam.izh.rd.online.entity.PossibleGameStates;

import java.util.Scanner;

public class UserRequestService {
    private Scanner scanner;

    public UserRequestService() {
        scanner = new Scanner(System.in);
    }

    public int getIntNumber() {
        if (!scanner.hasNextInt()) {
            return 0;
        }
        return scanner.nextInt();
    }

    public boolean allowNumberMatches(int matchesPerMove, int matchesOnTable, int maxMatchesPerMove) {
        return ((matchesPerMove >= 1) && (matchesPerMove <= Math.min(matchesOnTable - 1, maxMatchesPerMove)));
    }

    public int getMatches(GameProperties gameProperties, GameState gameState) {
        int chosenMatches = getIntNumber();
        if (allowNumberMatches(chosenMatches, gameState.getMatchesOnTable(), gameProperties.getMAX_MATCHES_PER_MOVE())) {
            return chosenMatches;
        }
        return 0;
    }

    public int getGameParameter(String paramDescription, int minValue, int maxValue) {
        int parameter;
        while (true) {
            System.out.printf(PossibleGameStates.INPUT_PARAMS.message, paramDescription, minValue);
            parameter = getIntNumber();
            if (parameter >= minValue && parameter <= maxValue) {
                break;
            }
            System.out.printf(PossibleGameStates.ERROR.message, paramDescription);
        }
        return parameter;
    }

    public int getGameParameter(String paramDescription, int minValue) {
        return getGameParameter(paramDescription, minValue, Integer.MAX_VALUE);
    }

    public String getUserName(int userID) {
        System.out.printf(PossibleGameStates.INPUT_USER.message, userID);
        if (scanner.hasNextInt()) {
            int chosenNumber = scanner.nextInt();
            switch (chosenNumber) {
                case 0:
                    return "Компьютер";
                case 1:
                    return "Вася";
                case 2:
                    return "Петя";
            }
        }
        return scanner.next();
    }
}