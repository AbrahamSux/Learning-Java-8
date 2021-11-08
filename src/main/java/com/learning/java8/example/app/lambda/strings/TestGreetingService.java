package com.learning.java8.example.app.lambda.strings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestGreetingService.class);



    // MÉTODOS.

    public static void main(String[] args) {

        // Sin paréntesis
        GreetingService greetService1 = message -> LOGGER.info("Hello " + message);
        greetService1.sayMessage("Abraham Juárez");

        // Entre paréntesis
        GreetingService greetService2 = (message) -> LOGGER.info("Hello " + message);
        greetService2.sayMessage("Diana Camarillo");



        MessagesService greetingService = (messageOne, messageTwo) -> messageOne.concat(" ").concat(messageTwo);

        LOGGER.info(greetingService.sayMessage("Hola", "Yelena Juárez"));
    }



    // INTERFACES.

    interface GreetingService {
        void sayMessage(String message);
    }

    interface MessagesService {
        String sayMessage(String say, String message);
    }

}
