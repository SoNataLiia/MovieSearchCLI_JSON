import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * В коде это метод fetchURL():
 *1. Создать объект URL из строки
 * 2. Открыть соединение → получить HttpURLConnection
 * 3. Сказать: метод = GET, принимаем JSON
 * 4. Открыть поток чтения (InputStream)
 * 5. Читать построчно → собрать в одну строку
 * 6. Закрыть соединение
 */
public class MoviMenu {
    private static String fetchURL(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        reader.close();
        conn.disconnect();
        return sb.toString();
    }
    private static String getJsonString(String json, String key) {
        String search = "\"" + key + "\":\"";
        int idx = json.indexOf(search);
        if (idx == -1) return null;
        int start = idx + search.length();
        int end = json.indexOf("\"", start);
        return json.substring(start, end);
    }
    private static void verPeliculasPopulares(Scanner sc) throws Exception {
//        System.out.print("Introduce cuántos pokemon quieres ver: ");
//        int n = Integer.parseInt(sc.nextLine().trim());

        String json = fetchURL("https://api.themoviedb.org/3/movie/popular?api_key=2dca580c2a14b55200e784d157207b4d");
        // Parsear el array "results" buscando todos los "name"
        int resultsIdx = json.indexOf("\"results\":");
        if (resultsIdx == -1) {
            System.out.println("Error al obtener los datos.");
            return;
        }
        String results = json.substring(resultsIdx);

        System.out.println("\n=== Lista de películas ===");
        int count = 0;
        int pos = 0;
        while ((pos = results.indexOf("\"title\":\"", pos)) != -1) {
            int start = pos + 9;
            int end = results.indexOf("\"", start);
            String title = results.substring(start, end);
            System.out.println((++count) + ". " + title);
            pos = end;
        }
    }
    private static void verDetallesPelicula(Scanner sc) throws Exception {
        System.out.print("Introduce el ID del película: ");
        int id = sc.nextInt();

        String json = fetchURL("https://api.themoviedb.org/3/movie/" + id + "?api_key=2dca580c2a14b55200e784d157207b4d");




        String title = getJsonString(json, "title");
        String overview = getJsonString(json, "overview");
        System.out.println("\nTítulo: " + title);
        System.out.println("Descripción: " + overview);


        // Dentro de abilities cada habilidad tiene {"ability":{"name":"...", ...}}
        int pos = 0;
        int count = 0;
        while ((pos = overview.indexOf("\"title\":\"", pos)) != -1) {
            int start = pos + 8;
            int end = overview.indexOf("\"", start);
            String ability = overview.substring(start, end);
            System.out.println("  - " + ability);
            pos = end;
            count++;
        }
        if (count == 0) System.out.println("  (Sin overview)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = - 1;

        do {
            System.out.println(" 1. Buscar películas populares");
            System.out.println(" 2. Ver detalle de película");
            System.out.println(" 0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion){
                    case 1:
                        verPeliculasPopulares(sc);
                        break;
                    case 2:
                        verDetallesPelicula(sc);
                        break;
                    case 0:
                        System.out.println("Hasta luego!");
                        break;
                    default:
                        System.out.println("Opcion no valida. Intenta de nuevo.");

                }
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
//            opcion = -1;

        } while (opcion != 0);
    }
}
