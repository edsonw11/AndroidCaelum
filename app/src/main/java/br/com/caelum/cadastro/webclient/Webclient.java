package br.com.caelum.cadastro.webclient;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by android6384 on 17/08/16.
 */
public class Webclient {


    public String post(String json){

        String jsonResposta = "";
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            //vai enviar "Content-type", "application/json
            connection.setRequestProperty("Content-type", "application/json");

            //espera como resposta "Accept", "application/json"
            connection.setRequestProperty("Accept", "application/json");


            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintStream saida = new PrintStream(connection.getOutputStream());

            saida.println(json);

            connection.connect();

            jsonResposta = new Scanner(connection.getInputStream()).next();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResposta;
    }
}
