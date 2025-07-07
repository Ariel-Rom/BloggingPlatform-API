package com.Ariel_Rom.BloggingPlatform_API;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestPostgresConnection {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/bloggingapidb";
        String user = "postgres";
        String password = "02142025";

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conexión exitosa!");
        conn.close();
    }
}
