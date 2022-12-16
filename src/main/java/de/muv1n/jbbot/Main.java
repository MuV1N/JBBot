package de.muv1n.jbbot;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dotenv dotenv = Dotenv.load();

        new JBBot(dotenv.get("TOKEN"));
    }
}