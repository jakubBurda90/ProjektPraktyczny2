package sda.orange.grcy.log4jDemo;

import org.apache.log4j.Logger;


public class log4jDemo {

    private final static Logger LOGGER= Logger.getLogger(log4jDemo.class);

    public static void main(String[] args) {

        System.out.println("Logowanie bez użycia Log4j");
        System.out.println("============================");
        System.out.println("============================");
        LOGGER.fatal("Fatal meseage logged");
    }
}
