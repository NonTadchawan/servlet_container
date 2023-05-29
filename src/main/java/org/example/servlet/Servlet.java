package org.example.servlet;

import java.io.IOException;

public interface Servlet {
    public void doGet(Request req,Response res)throws IOException;

}
