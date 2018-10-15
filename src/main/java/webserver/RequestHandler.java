package webserver;

import core.RequestLine;
import core.HttpMethod;
import core.RequestParam;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpHeaderUtils;
import util.IOUtils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    /*
     * Http Request 스펙
     *  Request Line
     *  Header
     *  CRLF
     *  Body
     */

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String firstHeaderLine = reader.readLine();
            if (firstHeaderLine == null) {
                return;
            }

            RequestLine requestLine = RequestLine.create(firstHeaderLine);

            Map<String, String> headers = new HashMap<>();

            while (reader.ready()) {
                String line = reader.readLine();
                log.debug(">>>>> {}", line);
                String[] headerToken = line.split((": "));
                if (headerToken.length == 2) {
                    headers.put(headerToken[0], headerToken[1]);
                }
            }

            log.debug(">> {}", reader.readLine());

            String path = requestLine.getPath();

            if ("/user/create".equals(path)) {
                RequestParam params;
                if (requestLine.getHttpMethod() == HttpMethod.GET) {
                    params = requestLine.getRequestParam();
                } else {
                    int contentLength = Integer.parseInt(headers.get("Content-Length"));
                    String s = IOUtils.readData(reader, contentLength);
                    Map<String, String> map = HttpHeaderUtils.parseKeyValue(s);
//                    params = new RequestParam(map);
                }

//                User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
//                log.debug("User={}", user);

                path = "/index.html";
            }


            DataOutputStream dos = new DataOutputStream(out);

            byte[] body = Files.readAllBytes(new File("./webapp" + path).toPath());
            response200Header(dos, body.length);
            responseBody(dos, body);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
