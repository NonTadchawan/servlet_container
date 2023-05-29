package org.example.servlet;

import java.io.*;

public class MyServlet implements Servlet{
    @Override
    public void doGet(Request req, Response res)throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(res.getOutputStream()));
        writer.write("Hello client");
        writer.newLine();
        writer.flush();
        writer.close();
    }
}
