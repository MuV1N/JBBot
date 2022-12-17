package de.muv1n.jbbot.database;

import de.muv1n.jbbot.JBBot;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStatments {

    private Statement statement;
    private Connection connection;
    private final JBBot bot;

    public DatabaseStatments(JBBot bot){
        this.bot = bot;
    }

    public void createTable() throws SQLException {
        this.statement = this.connection.createStatement();
    }

}
