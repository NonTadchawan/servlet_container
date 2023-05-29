package org.example.server;

import org.example.servlet.MyServlet;
import org.example.servlet.Request;
import org.example.servlet.Response;
import org.example.servlet.Servlet;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Map<Class, Servlet> servletMap = new HashMap<>();
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    System.out.println("accept client");
                    Class a = MyServlet.class;
                    Class<?> myClass = Class.forName("org.example.servlet.MyServlet");
                    if(!servletMap.containsKey(myClass)){
                        Constructor<?> constructor = myClass.getConstructor();
                        Servlet servlet = (Servlet) constructor.newInstance();
                        servletMap.put(myClass,servlet);

                        Method doGet = myClass.getDeclaredMethod("doGet", Request.class, Response.class);
                        doGet.invoke(servlet,new Request(socket.getInputStream()),new Response(socket.getOutputStream()));
                    }else {
                        Servlet servlet = servletMap.get(myClass);
                        servlet.doGet(new Request(socket.getInputStream()),new Response(socket.getOutputStream()));
                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
