package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpExchange;

public class DefaultParser implements HTTPExchangeParser {
    private HttpExchange exchange;

    DefaultParser(HttpExchange exchange){}

    public String getURLpath(){}
    public String getParameter(String method, String key){}
    public String parseBody(){}
}
