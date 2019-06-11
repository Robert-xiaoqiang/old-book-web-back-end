package xiaoqiang.wang.controller;

import java.io.Serializable;
import java.util.HashMap;

public class MyResponseBody implements Serializable {
    private final static long serialVersionID = 1l;

    private HashMap<String, Object> httpResponseBody;

    MyResponseBody(boolean status, String message, Object data)
    {
        httpResponseBody = new HashMap<>();
        httpResponseBody.put("status", status);
        httpResponseBody.put("message", message);
        httpResponseBody.put("data", data);
    }
}
