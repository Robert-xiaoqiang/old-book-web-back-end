package xiaoqiang.wang.controller;

import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;

@ResponseBody
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

    public HashMap<String, Object> getHttpResponseBody()
    {
        return httpResponseBody;
    }

    public void setHttpResponseBody(HashMap<String, Object> httpResponseBody)
    {
        this.httpResponseBody = httpResponseBody;
    }
}
