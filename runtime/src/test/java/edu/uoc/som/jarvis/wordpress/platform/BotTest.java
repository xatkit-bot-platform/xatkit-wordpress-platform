package edu.uoc.som.jarvis.wordpress.platform;

import edu.uoc.som.jarvis.Jarvis;

public class BotTest {

    public static void main(String[] args) throws InterruptedException {
        Jarvis.main(new String[]{"<path to the Jarvis properties file>"});

        Thread.sleep(100000000);
    }
}
