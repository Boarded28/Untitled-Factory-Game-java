package com.scrapitalism;

import com.scrapitalism.moose.GameWindow;

public class ScrapitalismGame {

    public static void main(String[] args) {
        GameWindow window = GameWindow.getInstance();
        window.setGameVersion(ScrapitalismGame.class.getPackage().getImplementationVersion());
        window.run();
    }
}

