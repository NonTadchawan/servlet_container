package org.example;

import org.example.server.Server;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        Server server = new Server();
        server.start();
    }
}
