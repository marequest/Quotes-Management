import app.RequestHandler;
import http.HttpMethod;
import http.Request;
import http.Server;
import http.response.Response;
import serialization.Quote;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.StringTokenizer;

public class PomocniServerThread implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public PomocniServerThread(Socket sock) {
        this.client = sock;

        try {
            //inicijalizacija ulaznog toka
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));

            //inicijalizacija izlaznog sistema
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            // uzimamo samo prvu liniju zahteva, iz koje dobijamo HTTP method i putanju
            String requestLine = in.readLine();

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            int contentLength = -1;
            System.out.println("\nHTTP ZAHTEV KLIJENTA:\n");
            do {
                System.out.println(requestLine);
                if (requestLine.contains("Content-Length:")) {
                    contentLength = Integer.parseInt(requestLine.substring(16).trim());
                }
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            if (method.equals(HttpMethod.POST.toString())) {
//                 TODO: Ako je request method POST, procitaj telo zahteva (parametre)
            }


            Request request = new Request(HttpMethod.valueOf(method), path);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP odgovor:\n");
            System.out.println(response.getResponseString());

            out.println(response.getResponseString());

            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
