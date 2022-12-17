package de.muv1n.jbbot;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Dotenv dotenv = Dotenv.load();

        new JBBot(dotenv.get("TOKEN"));
    }
}