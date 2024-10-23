package conversormoneda.modulos;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class SolicitudMoneda {
    public static void main(String[] args) {
        //Creamos las variables
        int eleccion = 0;
        int cantidad = 0;
        String monedaOrigen = "";
        String monedaDestino = "";
        String direccion = "https://v6.exchangerate-api.com/v6/9907bff63798f348f1bbeb77/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;
        Scanner teclado = new Scanner(System.in);

        while(eleccion != 7){
            //Creamos el inicio de la aplicación presentado el menú
            String menu = """
                **********  Conversor de Moneda  **********
                Elige una opción: 
                1 - Dólar           => Peso Colombiano
                2 - Peso Colombiano => Dólar
                3 - Dólar           => Peso argentino
                4 - Peso argentino  => Dólar
                5 - Dólar           => Real brasileño
                6 - Real brasileño  => Dólar
                7 - Salir
                """;
            System.out.println(menu);
            //capturamos el valor digitado por el usuario con el teclado y lo almacenamos en la variable eleccion
            eleccion = teclado.nextInt();

            if (eleccion == 7){
                System.out.println("Gracias por utilizar nuestra aplicación");
                break;
            }

            System.out.println("Escribe la cantidad: ");
            cantidad = teclado.nextInt();

            //En un switch elegimos el número del caso digitado por el usuario
            switch(eleccion){
                case 1:
                    monedaOrigen = "usd";
                    monedaDestino = "cop";
                    break;
                case 2:
                    monedaOrigen = "cop";
                    monedaDestino = "usd";
                    break;
                case 3:
                    monedaOrigen = "usd";
                    monedaDestino = "ars";
                    break;
                case 4:
                    monedaOrigen = "ars";
                    monedaDestino = "usd";
                    break;
                case 5:
                    monedaOrigen = "usd";
                    monedaDestino = "brl";
                    break;
                case 6:
                    monedaOrigen = "brl";
                    monedaDestino = "usd";

                    break;
                default:
                    System.out.println("Seleccione un valor válido en el menú");
                    break;
            }

            direccion = "https://v6.exchangerate-api.com/v6/9907bff63798f348f1bbeb77/pair/" + monedaOrigen + "/" + monedaDestino + "/" + cantidad;

            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                //System.out.println(json);

                Gson gson = new Gson();
                Presentacion miPresentacion = gson.fromJson(json, Presentacion.class);

                System.out.print(cantidad);
                System.out.println(miPresentacion);

            }catch(Exception e){
                System.out.println("Error! Verifique los valores elegidos");
            }
        }


    }

}
