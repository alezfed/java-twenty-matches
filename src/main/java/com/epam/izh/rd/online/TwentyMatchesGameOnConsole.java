package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.ProcessGameService;

public class TwentyMatchesGameOnConsole {

    public static void main(String[] args) {
        ProcessGameService processGameService = new ProcessGameService();
        processGameService.startGame();
    }
}