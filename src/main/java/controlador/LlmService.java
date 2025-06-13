package controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

import config.ConfigLoader;

public class LlmService {
	// Método que recibe una pregunta y devuelve la respuesta del modelo IA
	public static String obtenerRespuesta(String pregunta) {
		try {
			// URL de la API a la que se va a conectar
			URL url = new URL("https://openrouter.ai/api/v1/chat/completions");
			
			// Abrir conexión HTTP con la URL
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// Establecer método POST para enviar datos
			connection.setRequestMethod("POST");
			
			// Permitir enviar datos en el cuerpo de la petición
			connection.setDoOutput(true);

			// Obtener la API key desde el archivo config.properties con la clave "apikey"
			String api = ConfigLoader.getProperty("apikey");
			
			// Verificar que la API key exista y no esté vacía
			if (api == null || api.isEmpty()) {
				throw new RuntimeException("API key no encontrada en config.properties");
			}

			// Configurar la cabecera Authorization con el token Bearer
			connection.setRequestProperty("Authorization", "Bearer " + api);
			
			// Indicar que el contenido enviado es JSON
			connection.setRequestProperty("Content-Type", "application/json");

			// Escapar las comillas en la pregunta para evitar errores JSON
			String contenido = pregunta.replace("\"", "\\\"");

			// Construir el cuerpo JSON que enviará la petición a la API
			String jsonInput = String.format(
				"{\"model\":\"openai/gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}",
				contenido);

			// Enviar el JSON al servidor usando el OutputStream de la conexión
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// Obtener el código de respuesta HTTP
			int responseCode = connection.getResponseCode();

			// Si la respuesta es OK (200), leer la respuesta del servidor
			if (responseCode == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;

				// Leer línea por línea la respuesta JSON
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}

				// Parsear la respuesta JSON
				JSONObject jsonResponse = new JSONObject(response.toString());
				
				// Extraer el array "choices"
				JSONArray choices = jsonResponse.getJSONArray("choices");
				
				// Tomar el primer elemento del array
				JSONObject firstChoice = choices.getJSONObject(0);
				
				// Obtener el objeto "message" dentro de la elección
				JSONObject message = firstChoice.getJSONObject("message");

				// Devolver el contenido del mensaje, que es la respuesta del modelo IA
				return message.getString("content");

			} else {
				// Si la respuesta no es 200, leer el error que devuelve la API
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				StringBuilder errorResponse = new StringBuilder();
				String line;
				while ((line = errorReader.readLine()) != null) {
					errorResponse.append(line);
				}
				// Construir mensaje de error con el código HTTP y el detalle
				String error = "Error " + responseCode + ": " + errorResponse.toString();
				
				// Si el error es por límite de uso de la API, informar mensaje amigable
				if (error.contains("rate limit")) {
					return "Por favor espere y vuelva a intentarlo más tarde (límite de API)";
				}
				// Devolver el error tal cual para otros casos
				return error;
			}
		} catch (Exception e) {
			// Capturar cualquier excepción y devolverla como mensaje de error
			return "Error en servicio IA: " + e.getMessage();
		}
	}
}

