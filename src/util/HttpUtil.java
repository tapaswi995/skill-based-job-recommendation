package util;

import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpUtil {
    public static Map<String, String> parameters(HttpExchange exchange) throws IOException {
        String input = exchange.getRequestURI().getRawQuery();
        if ("POST".equalsIgnoreCase(exchange.getRequestMethod()) || "PUT".equalsIgnoreCase(exchange.getRequestMethod()))
            input = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> values = new HashMap<>();
        if (input != null) for (String pair : input.split("&")) {
            String[] parts = pair.split("=", 2);
            values.put(decode(parts[0]), parts.length > 1 ? decode(parts[1]) : "");
        }
        return values;
    }
    private static String decode(String value) { return URLDecoder.decode(value, StandardCharsets.UTF_8); }
    public static void json(HttpExchange exchange, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream output = exchange.getResponseBody()) { output.write(bytes); }
    }
    public static String esc(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }
    public static Integer number(Map<String,String> p, String key) {
        try { return Integer.parseInt(p.get(key)); } catch (Exception e) { return null; }
    }
}
