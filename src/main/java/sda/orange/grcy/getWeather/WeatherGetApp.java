package sda.orange.grcy.getWeather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class WeatherGetApp {

    private static final String GET_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/275174?" +
            "apikey=fJ4AjYygVoiSop0mB9AmxzGCIFjjalZr&metric=true";

    public static void main(String[] args) {

        /**
         * Doróbcie pętle w której będzie można "sterować programem"
         * -wciśnieńcie 0 kończy program
         * -wciśnieńcie 1 zaciąga nowe dane o pogodzie
         * -wciśnieńcie 2 wyświetla informacje informacje pogodowe na ekranie
         * Wszystkie powyższe akcje mająbyć przekazane do klasy kontrolera (z wyjątkiem 0)
         *
         * Dorobić "kontroler" który będzie reagował na "menu" - akcje użytkownika
         * - jak macie pobrać nowe dane to wysyłacie żadanie URL i zapisujecie do obiektu pogody
         * - jak macie wyświetlić to wyciągacie dane z obiektu pogody
         */

        try {
            URL obj = new URL(GET_URL);
            HttpURLConnection conn =(HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            /**
             * HTTP;
             * GET - POBIERANIE DANYCH (PO BAZODANOWEMU - SELECT)
             *
             * POST - WSTAWIENIE (PIERWSZE) DANYCH - (INSERT)
             * PUT - KOREKTA (WSZYSTKICH) DANYCH - (UPDATE/ MERGE)
             * PATCH - KOREKTA POJEDYNCZYCH PÓL - (UPDATE / MERGE)
             *
             * DELETE - KASOWANIE DANYCH (DELETE)
             *
             * INFO - POBIERANIE INFORMACJI - NP NA TEMAT DOSTEPNYCH TZW. ENDPOINTOW CZYLI ADRESOW POD KTORYMI MOZNA WYSŁAC GET, POST, ITP...
             */
            int responseCode = conn.getResponseCode();


            /**
             * Kody odpowiedzi http:
             * 100 - info
             * 200 - ok
             * 300 - przekierowanie
             * 400 - błąd użytkownika
             * 500 - błąd serwera
             */

            System.out.println("Kod odpowiedzi " + conn.getResponseCode());
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputline;
                StringBuffer response = new StringBuffer();

                while((inputline = reader.readLine()) !=null){
                    response.append(inputline);
                }
                reader.close();
                System.out.println("Pogoda w Gdańsku :");
                System.out.print(response);


                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> weatherResultMap = mapper.readValue
                        (response.toString(), new TypeReference<Map<String, Object>>() {});
                System.out.println(weatherResultMap);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
