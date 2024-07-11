import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ConversorDeMoedas {

    private static final String ACCESS_KEY = "b4a8c0637f20530144a1610";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao Conversor de Moedas!");

            
            System.out.print("Digite a moeda que você quer converter (ex: EUR, USD, BRL, JPY, GBP, AUD, CHF, ARS): ");
            String de = scanner.next().toUpperCase();
            System.out.print("Digite a moeda de destino para ser convertida: ");
            String para = scanner.next().toUpperCase();
            System.out.print("Digite o valor a ser convertido: ");
            double quantia = scanner.nextDouble();

            convertCurrency(de, para, quantia);
        }
    }
    
    private static double convertCurrency(String de, String para, double quantia) {
    try {
        URL url = new URL(BASE_URL + ACCESS_KEY + "/pair/" + de + "/" + para);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            double conversionRate = json.getDouble("conversion_rate");
            return conversionRate;
        } else {
            System.out.println("Erro ao obter a taxa de câmbio.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0.0; // Retornar 0.0 em caso de erro
}

    // private static void convertCurrency(String de, String para, double quantia) {
    //     try {
            
    //         URL url = new URL(BASE_URL + ACCESS_KEY + "/pair/" + de + "/" + para);
    //         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //         connection.setRequestMethod("GET");
          
    //         int responseCode = connection.getResponseCode();
    //         if (responseCode == 200) {
    //             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //             String line;
    //             StringBuilder response = new StringBuilder();
    //             while ((line = reader.readLine()) != null) {
    //                 response.append(line);
    //             }
    //             reader.close();

    //             System.out.println(quantia + " " + de + " é igual a X " + para);

    //         } else {
    //             System.out.println("Erro ao obter a taxa de câmbio.");
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
}

