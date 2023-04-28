package http;

import serialization.Quote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static final int TCP_PORT = 8080;

    public static ArrayList<Quote> quotes;

    public static void main(String[] args) {
        quotes = new ArrayList<>();
//        quotes.add(new Quote("Nelson Mandela", "The greatest glory in living lies not in never falling, but in rising every time we fall."));
//        quotes.add(new Quote("Walt Disney", "The way to get started is to quit talking and begin doing."));
//        quotes.add(new Quote("Eleanor Roosevelt", "If life were predictable it would cease to be life, and be without flavor."));
//        quotes.add(new Quote("Oprah Winfrey", "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough."));
//        quotes.add(new Quote("James Cameron", "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success."));

        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new ServerThread(sock)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
