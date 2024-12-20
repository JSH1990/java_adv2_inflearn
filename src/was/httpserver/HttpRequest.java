package was.httpserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import static java.nio.charset.StandardCharsets.*;
import static util.MyLogger.log;
public class HttpRequest {
    private String method;
    private String path;
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);
// 메시지 바디는 이후에 처리
    }

    //GET /search?q=hello HTTP/1.1
    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine(); //한줄씩 읽기
        if (requestLine == null) {
            throw new IOException("EOF: No request line received");
        }
        String[] parts = requestLine.split(" "); //빈칸 자르기
        if (parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }
        method = parts[0]; //GET
        String[] pathParts = parts[1].split("\\?"); //search?q=hello
        path = pathParts[0];
        if (pathParts.length > 1) { //key1=value1&key2=value2
            parseQueryParameters(pathParts[1]);
        }
    }

    //key1=value1 & key2=value2
    //키1=값1 -> %키1=%값1
    //q= (값이 없을때)
    private void parseQueryParameters(String queryString) {
        for (String param : queryString.split("&")) {
            String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], UTF_8); //키1=값1 -> %키1=%값1 이렇게 들어올수도 있음
            String value = keyValue.length > 1 ?
                    URLDecoder.decode(keyValue[1], UTF_8) : "";
            queryParameters.put(key, value);
        }
    }
    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] headerParts = line.split(":");
// trim() 앞 뒤에 공백 제거
            headers.put(headerParts[0].trim(), headerParts[1].trim());
        }
    }
    public String getMethod() {
        return method;
    }
    public String getPath() {
        return path;
    }
    public String getParameter(String name) {
        return queryParameters.get(name);
    }
    public String getHeader(String name) {
        return headers.get(name);
    }
    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}
